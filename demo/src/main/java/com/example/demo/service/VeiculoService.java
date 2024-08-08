package com.example.demo.service;

import com.example.demo.dto.request.ParamsVeiculo;
import com.example.demo.dto.request.VeiculoRequestDTO;
import com.example.demo.dto.response.VeiculoDTO;
import com.example.demo.model.Marca;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    MarcaService marcaService;

    public Page<VeiculoDTO> getVeiculos(ParamsVeiculo paramsVeiculo, Pageable pageable) {
        var veiculos = veiculoRepository.buscar(paramsVeiculo.marca(), paramsVeiculo.ano(), paramsVeiculo.isVendido(), pageable);
        return new PageImpl<>(
                converterLista(veiculos.getContent()),
                pageable,
                veiculos.getTotalElements()
        );
    }

    private List<VeiculoDTO> converterLista(List<Veiculo> itens) {
        var itensListaDTO = new ArrayList<VeiculoDTO>();

        for (Veiculo veiculo : itens) {
            var veiculoDTO = new VeiculoDTO(veiculo);
            itensListaDTO.add(veiculoDTO);
        }

        return itensListaDTO;
    }

    public Boolean deletar(Long id) {
        Optional<Veiculo> catalogoItem = veiculoRepository.findById(id);

        if (catalogoItem.isPresent() && catalogoItem.get().isAtivo()) {
            veiculoRepository.delete(catalogoItem.get());
            return true;
        }

        return false;
    }

    @Transactional
    public void salvar(VeiculoRequestDTO veiculoRequestDTO) {

        Marca marca = marcaService.getMarca(veiculoRequestDTO.marca());

        if (marca == null) {
            throw new RuntimeException("Não foi possível salvar - Marca");
        }

        Veiculo veiculo = new Veiculo(veiculoRequestDTO);
        veiculo.setMarca(marca);

        veiculoRepository.save(veiculo);
    }

    @Transactional
    public VeiculoDTO editar(Long id, VeiculoRequestDTO veiculoDTO) {

        Optional<Veiculo> veiculoRecuperado = veiculoRepository.findById(id);

        if (veiculoRecuperado.isEmpty() || !veiculoRecuperado.get().isAtivo()) {
            throw new RuntimeException("Não foi possível editar");
        }

        Marca marca = marcaService.getMarca(veiculoDTO.marca());

        if (marca == null) {
            throw new RuntimeException("Não foi possível salvar - Marca");
        }

        var veiculo = veiculoRecuperado.get();
        veiculo.setAno(veiculoDTO.ano());
        veiculo.setDescricao(veiculoDTO.descricao());
        veiculo.setMarca(marca);

        return new VeiculoDTO(veiculoRepository.save(veiculo));
    }

    @Transactional
    public VeiculoDTO vender(Long id) {

        Optional<Veiculo> veiculoRecuperado = veiculoRepository.findById(id);

        if (veiculoRecuperado.isEmpty() || !veiculoRecuperado.get().isAtivo() || veiculoRecuperado.get().isVendido()) {
            throw new RuntimeException("Não foi possível vender");
        }

        var veiculo = veiculoRecuperado.get();
        veiculo.setVendido(true);

        return new VeiculoDTO(veiculoRepository.save(veiculo));
    }
}

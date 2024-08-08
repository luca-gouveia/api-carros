package com.example.demo.dto.response;

import com.example.demo.model.Veiculo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO {
    private Long id;
    private String marca;
    private String descricao;
    private Integer ano;
    private Boolean isVendido;

    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca().getNome();
        this.descricao = veiculo.getDescricao();
        this.ano = veiculo.getAno();
        this.isVendido = veiculo.isVendido();
    }
}

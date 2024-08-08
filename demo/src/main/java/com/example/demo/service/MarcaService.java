package com.example.demo.service;

import com.example.demo.dto.response.MarcaDTO;
import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public Marca getMarca(String sigla) {
        return marcaRepository.findBySigla(sigla).orElse(null);
    }

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }
}

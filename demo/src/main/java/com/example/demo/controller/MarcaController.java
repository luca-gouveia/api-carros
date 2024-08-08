package com.example.demo.controller;

import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @Operation(summary = "Recuperar todas as marcas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Falha"),
    })
    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas() {
        return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
    }
}

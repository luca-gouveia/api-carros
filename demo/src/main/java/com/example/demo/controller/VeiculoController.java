package com.example.demo.controller;

import com.example.demo.dto.request.ParamsVeiculo;
import com.example.demo.dto.request.VeiculoRequestDTO;
import com.example.demo.dto.response.VeiculoDTO;
import com.example.demo.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;

    @Operation(summary = "Recupera veículos de acordo com os parâmetros", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "500", description = "falha"),
    })
    @GetMapping
    public ResponseEntity<Page<VeiculoDTO>> getVeiculos(@ParameterObject ParamsVeiculo paramsVeiculo,
                                                        @PageableDefault(value = 50) @ParameterObject Pageable pageable) {
        var veiculos = veiculoService.getVeiculos(paramsVeiculo, pageable);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @Operation(summary = "Salvar veículo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar"),
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void avaliarTitulo(@RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        veiculoService.salvar(veiculoRequestDTO);
    }

    @Operation(summary = "Realiza a remoção lógica", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a remoção"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        var isDeletado = veiculoService.deletar(id);

        if (isDeletado) {
            return ResponseEntity.ok().body("Veículo removido!");
        } else {
            throw new RuntimeException("Não foi possível remover");
        }
    }

    @Operation(summary = "Edita veiculo", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o processamento da edição"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> editar(@PathVariable("id") Long id, @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        return ResponseEntity.ok().body(veiculoService.editar(id, veiculoRequestDTO));
    }

    @Operation(summary = "Vender veiculo", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o processamento edição - VENDA"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoDTO> vender(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(veiculoService.vender(id));
    }
}

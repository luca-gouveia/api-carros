package com.example.demo.repository;

import com.example.demo.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findById(long id);

    @Query("FROM veiculo v " +
            "WHERE (:marca IS NULL OR (v.marca.sigla LIKE %:marca%)) " +
            "AND " +
            "(:ano IS NULL OR (v.ano = :ano)) " +
            "AND " +
            "(:isVendido IS NULL OR (v.isVendido = :isVendido)) " +
            "AND " +
            "(v.isAtivo = TRUE)"
    )
    Page<Veiculo> buscar(@Param("marca") String marca,
                            @Param("ano") Integer ano,
                            @Param("isVendido") Boolean isVendido,
                            Pageable pageable);

}

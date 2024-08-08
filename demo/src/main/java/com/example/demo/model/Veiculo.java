package com.example.demo.model;

import com.example.demo.dto.request.VeiculoRequestDTO;
import com.example.demo.dto.response.VeiculoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "veiculo")
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE veiculo SET IS_ATIVO = false WHERE id = ?")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca", referencedColumnName = "id")
    private Marca marca;

    private int ano;

    private String descricao;

    private boolean isVendido;

    private boolean isAtivo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Veiculo(VeiculoRequestDTO veiculoRequestDTO) {
        this.ano = veiculoRequestDTO.ano();
        this.descricao = veiculoRequestDTO.descricao();
        this.isAtivo = true;
    }
}

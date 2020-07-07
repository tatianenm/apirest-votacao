package com.votacao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "votacao")
@Entity
public class VotacaoEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JsonBackReference
    @Column(name = "idpauta")
    private PautaEntity pautaEntity;

    @OneToMany
    @JsonBackReference
    @Column(name = "idassociado")
    private AssociadoEntity associadoEntity;

    @Column(name = "voto")
    private String voto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoEntity that = (VotacaoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pautaEntity, that.pautaEntity) &&
                Objects.equals(associadoEntity, that.associadoEntity) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

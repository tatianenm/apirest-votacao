package com.votacao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "votacao")
@Entity
public class VotacaoEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idpauta")
    private PautaEntity pautaEntity;


    @OneToMany(mappedBy = "votacao")
    @JsonManagedReference
    private List<AssociadoEntity> associados;

    @Column(name = "voto")
    private String voto;

    @Column(name = "data")
    private LocalDate dataSistema;

    @OneToOne
    @JoinColumn(name = "idsessao")
    private SessaoEntity sessaoEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoEntity that = (VotacaoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pautaEntity, that.pautaEntity) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

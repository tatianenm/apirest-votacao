package com.votacao.entity;

import com.votacao.domain.VotoEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    @JoinColumn(name = "idassociado")
    private AssociadoEntity associado;

    @Column(name = "voto")
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;

    @Column(name = "data")
    private LocalDate dataSistema;

    @OneToOne
    @JoinColumn(name = "idsessao")
    private SessaoEntity sessao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoEntity that = (VotacaoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

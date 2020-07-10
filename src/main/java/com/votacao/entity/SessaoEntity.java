package com.votacao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.votacao.domain.StatusSessaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sessao")
public class SessaoEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "datahorainicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "statussessao")
    @Enumerated(EnumType.STRING)
    private StatusSessaoEnum statusSessao;

    @Column(name = "datahorafim")
    private LocalDateTime dataHoraFim;

    @ManyToOne
    @JoinColumn(name = "idpauta")
    private PautaEntity pauta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessaoEntity that = (SessaoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

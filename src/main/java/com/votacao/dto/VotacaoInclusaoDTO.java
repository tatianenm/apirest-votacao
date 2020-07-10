package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votacao.domain.VotoEnum;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.SessaoEntity;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private AssociadoDTO associado;

    private VotoEnum voto;

    private SessaoDTO sessao;

    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSistema = LocalDate.now();
}

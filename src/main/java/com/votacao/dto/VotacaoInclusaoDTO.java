package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votacao.domain.VotoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
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

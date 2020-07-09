package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

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
public class VotacaoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private PautaDTO pautaDTO;

    private AssociadoDTO associadoDTO;

    private String voto;

    @ApiModelProperty(hidden = true)
    private LocalDate dataSistema = LocalDate.now();
}

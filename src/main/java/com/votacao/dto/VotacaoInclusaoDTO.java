package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VotacaoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private PautaInclusaoDTO pautaDTO;

    private AssociadoInclusaoDTO associadoDTO;

    private String voto;

    @ApiModelProperty(hidden = true)
    private LocalDate data = LocalDate.now();
}

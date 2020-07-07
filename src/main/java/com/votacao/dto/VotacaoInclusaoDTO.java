package com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VotacaoInclusaoDTO {

    private Long id;

    private PautaInclusaoDTO pautaDTO;

    private AssociadoInclusaoDTO associadoDTO;

    private String voto;
}

package com.votacao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PautaInclusaoDTO {

    private Long id;

    private String nomePauta;
}

package com.votacao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PautaListaDTO {

    private Long id;

    private String nomePauta;
}

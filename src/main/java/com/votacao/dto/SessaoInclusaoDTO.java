package com.votacao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SessaoInclusaoDTO {

    private Long id;

    private Integer validade;

}

package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class AssociadoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nome;

    private String cpf;

}

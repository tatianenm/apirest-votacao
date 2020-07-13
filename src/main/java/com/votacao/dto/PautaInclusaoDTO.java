package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PautaInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nomePauta;
}

package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class PautaInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nomePauta;
}

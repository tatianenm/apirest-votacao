package com.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nome;

    private String cpf;

}

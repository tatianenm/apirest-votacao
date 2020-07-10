package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private PautaDTO pauta;

    private AssociadoDTO associado;

    private String voto;

    @ApiModelProperty(hidden = true)
   // @ApiModelProperty(example = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSistema = LocalDate.now();
}

package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votacao.domain.VotoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoInclusaoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private AssociadoDTO associado;

    private VotoEnum voto;

    private SessaoDTO sessao;

    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSistema = LocalDateTime.now();
}

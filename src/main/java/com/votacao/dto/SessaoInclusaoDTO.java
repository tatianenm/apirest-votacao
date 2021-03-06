package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votacao.domain.StatusSessaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoInclusaoDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataHoraInicio;

    private StatusSessaoEnum statusSessao;

    private PautaDTO pauta;

    private Integer validadeMinutos;

}

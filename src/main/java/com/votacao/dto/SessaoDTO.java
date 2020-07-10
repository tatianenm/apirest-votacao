package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votacao.domain.StatusSessaoEnum;
import com.votacao.entity.PautaEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataHoraInicio;

    private StatusSessaoEnum statusSessao;

    private LocalDateTime dataHoraFim;

    private PautaEntity pauta;

}

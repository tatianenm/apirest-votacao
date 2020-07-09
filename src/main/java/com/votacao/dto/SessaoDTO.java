package com.votacao.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDTO {

    private Long id;

    private LocalDateTime dataHoraInicio;

    private String statusSessao;
}

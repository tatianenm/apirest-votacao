package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VotacaoInclusaoDTO {

    private Long id;

    private PautaInclusaoDTO pautaDTO;

    private List<AssociadoInclusaoDTO> associados;

    private String voto;
}

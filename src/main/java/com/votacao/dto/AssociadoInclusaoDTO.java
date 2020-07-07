package com.votacao.dto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociadoInclusaoDTO {

    private Long id;

    private String nome;
    
    private VotacaoInclusaoDTO votacaoInclusaoDTO;
}

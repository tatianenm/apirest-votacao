package com.votacao.repository;

import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VotacaoRepository extends CrudRepository<VotacaoEntity,Long> {

    Boolean findByDataSistemaAndPautaDTOAndAssociadoDTO(LocalDate data, PautaInclusaoDTO pautaDTO, AssociadoInclusaoDTO associadoDTO);
}

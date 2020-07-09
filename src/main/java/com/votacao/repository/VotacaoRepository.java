package com.votacao.repository;

import com.votacao.entity.VotacaoEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VotacaoRepository extends CrudRepository<VotacaoEntity,Long> {

    VotacaoEntity findByDataSistemaAndPautaEntityAndAssociados_Id(LocalDate data, Long idPauta, Long idAssociado);
}

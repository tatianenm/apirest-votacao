package com.votacao.repository;

import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.entity.VotacaoEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VotacaoRepository extends CrudRepository<VotacaoEntity,Long> {

    VotacaoEntity findByDataSistemaAndAssociadoAndSessao(LocalDate data, AssociadoEntity associado, SessaoEntity sessao);
}

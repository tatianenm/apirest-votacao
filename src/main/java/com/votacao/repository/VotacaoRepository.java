package com.votacao.repository;

import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.PautaEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.entity.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VotacaoRepository extends JpaRepository<VotacaoEntity,Long> {

    boolean existsByDataSistemaAndAssociadoAndSessao(LocalDateTime data, AssociadoEntity associado, SessaoEntity sessao);

    List<VotacaoEntity> findBySessao_Pauta(PautaEntity pauta);
}

package com.votacao.repository;

import com.votacao.domain.VotoEnum;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VotacaoRepository extends JpaRepository<VotacaoEntity, Long> {

    boolean existsByDataSistemaAndAssociado_IdAndSessao_Id(LocalDate data, Long idAssociado, Long idSessao);

    List<VotacaoEntity> findBySessao_Pauta_id(Long idPauta);

    Long countBySessao_IdAndVoto(Long idSessao, VotoEnum votoEnum);

    VotacaoEntity findDistinctByAssociado_Id(Long id);
}

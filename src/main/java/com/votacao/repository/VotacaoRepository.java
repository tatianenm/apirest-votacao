package com.votacao.repository;

import com.votacao.domain.VotoEnum;
import com.votacao.entity.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VotacaoRepository extends JpaRepository<VotacaoEntity, Long> {

    boolean existsByDataSistemaAndAssociado_IdAndSessao_Id(LocalDateTime data, Long idAssociado, Long idSessao);

    List<VotacaoEntity> findBySessao_Pauta_id(Long idPauta);

    Long countBySessao_IdAndVoto(Long idSessao, VotoEnum votoEnum);
}

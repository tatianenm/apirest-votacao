package com.votacao.repository;

import com.votacao.entity.VotacaoEntity;
import org.springframework.data.repository.CrudRepository;

public interface VotacaoRepository extends CrudRepository<VotacaoEntity,Long> {
}

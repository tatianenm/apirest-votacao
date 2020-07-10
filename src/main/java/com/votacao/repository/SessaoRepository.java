package com.votacao.repository;

import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.SessaoEntity;
import org.springframework.data.repository.CrudRepository;

public interface SessaoRepository extends CrudRepository<SessaoEntity, Long> {
}

package com.votacao.repository;

import com.votacao.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {
}

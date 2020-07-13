package com.votacao.service;

import com.votacao.converter.SessaoConverter;
import com.votacao.domain.StatusSessaoEnum;
import com.votacao.entity.PautaEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.exception.SessaoException;
import com.votacao.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;

    private SessaoConverter sessaoConverter;

    public SessaoService(SessaoRepository sessaoRepository, SessaoConverter sessaoConverter) {
        this.sessaoRepository = sessaoRepository;
        this.sessaoConverter = sessaoConverter;
    }

    public SessaoEntity iniciarSessao(Long idPauta, Integer validade) {
        if (validade == null || validade < 1) {
            validade = 1;
        }
        var sessaoEntity = SessaoEntity.builder()
                .dataHoraInicio(LocalDateTime.now())
                .statusSessao(StatusSessaoEnum.INICIALIZADA)
                .pauta(PautaEntity.builder().id(idPauta).build())
                .validade(validade)
                .build();
        return sessaoRepository.save(sessaoEntity);
    }

    public SessaoEntity finalizarSessao(Long idSessao) {
        var sessao = findById(idSessao);
        sessao.setStatusSessao(StatusSessaoEnum.FINALIZADA);
        return sessaoRepository.save(sessao);
    }

    public Boolean verificaSessaoExpirada(Long idSessao) {
        var sessao = findById(idSessao);
        var minutos = ChronoUnit.MINUTES.between(sessao.getDataHoraInicio(), LocalDateTime.now());
        var expirado = minutos > sessao.getValidade();
        if (expirado) {
            finalizarSessao(idSessao);
        }
        return expirado;
    }

    private SessaoEntity findById(Long idSessao) {
        var sessaoOpt = sessaoRepository.findById(idSessao);
        return sessaoOpt.orElseThrow(() -> new SessaoException("Sessão não Encontrada"));
    }
}

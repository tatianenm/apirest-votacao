package com.votacao.convertertest;

import com.votacao.converter.AssociadoConverter;
import com.votacao.converter.SessaoConverter;
import com.votacao.converter.VotacaoConverter;
import com.votacao.domain.VotoEnum;
import com.votacao.dto.AssociadoDTO;
import com.votacao.dto.SessaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.entity.VotacaoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class VotacaoConverterTest {

    private static final Long ID = 2L;

    private static final Long ID_SESSAO = 3L;


    @InjectMocks
    private VotacaoConverter votacaoConverter;
    @Mock
    private SessaoConverter sessaoConverter;
    @Mock
    private AssociadoConverter associadoConverter;
    @Mock
    private AssociadoEntity associadoEntity;
    @Mock
    private SessaoEntity sessaoEntity;

    @Test
    public void deveConverterToEntity() {
        var inclusaoDTO = VotacaoInclusaoDTO.builder()
                .id(ID)
                .sessao(SessaoDTO.builder().id(ID_SESSAO).build())
                .associado(AssociadoDTO.builder().id(6L).build())
                .dataSistema(LocalDate.now())
                .voto(VotoEnum.SIM)
                .build();

        VotacaoEntity votacao = votacaoConverter.convertToEntity(inclusaoDTO);

        Assert.assertNotNull(votacao);
        Assert.assertEquals(votacao.getSessao(), inclusaoDTO.getSessao());
        Assert.assertEquals(votacao.getDataSistema(), inclusaoDTO.getDataSistema());
        Assert.assertEquals(votacao.getVoto(), inclusaoDTO.getVoto());

    }

    private SessaoEntity mockSessao(){
        return SessaoEntity.builder()
                .id(2L)
                .build();
    }


}

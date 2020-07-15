package com.votacao.convertertest;

import com.votacao.converter.AssociadoConverter;
import com.votacao.converter.PautaConverter;
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
import org.mockito.Mockito;
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

    @Mock
    private PautaConverter pautaConverter;

    @Test
    public void deveConverterToEntity() {
        var inclusaoDTO = VotacaoInclusaoDTO.builder()
                .id(ID)
                .sessao(SessaoDTO.builder().id(ID_SESSAO).build())
                .associado(AssociadoDTO.builder().id(6L).build())
                .dataSistema(LocalDate.now())
                .voto(VotoEnum.SIM)
                .build();
        Mockito.doCallRealMethod().when(sessaoConverter).convertToEntity(Mockito.any(SessaoDTO.class));

        VotacaoEntity votacao = votacaoConverter.convertToEntity(inclusaoDTO);

        Assert.assertNotNull(votacao);
        Assert.assertEquals(votacao.getSessao().getId(), inclusaoDTO.getSessao().getId());
        Assert.assertEquals(votacao.getDataSistema(), inclusaoDTO.getDataSistema());
        Assert.assertEquals(votacao.getVoto(), inclusaoDTO.getVoto());
        Mockito.verify(sessaoConverter, Mockito.times(1)).convertToEntity(Mockito.any(SessaoDTO.class));

    }

}

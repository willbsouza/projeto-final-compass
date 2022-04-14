package com.compass.projetodoacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.compass.projetodoacao.dto.SolicitacaoDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Donatario;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Solicitacao;
import com.compass.projetodoacao.entities.enums.Tipo;
import com.compass.projetodoacao.repositories.DonatarioRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.repositories.SolicitacaoRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest

public class SolicitacaoServiceTest {
	@InjectMocks
	private SolicitacaoService solicitacaoService;

	@Mock
	private SolicitacaoRepository solicitacaoRepository;

	@Mock
	private ONGRepository ongRepository;

	@Mock
	private DonatarioRepository donatarioRepository;

	@Mock
	private ItemService itemService;

	private Categoria categoria;

	private Item item;

	private Solicitacao solicitacao;

	private SolicitacaoFormDTO solicitacaoFormDTO;

	private ONG ong;

	private Optional<Solicitacao> solicitacaoOpt;

	private Donatario donatario;
	private Optional<ONG> ongOpt;
	private Optional<Donatario> donatarioOpt;
	private static final Integer QUANTIDADETOTAL = 10;
	private static final Integer ID = 1;
	private static final String FILIAL = "Recife";
	private static final String NOME = "João";
	private static final String CPF = "12345678950";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarSolicitacao();
	}

	@Test
	void deveriaRetornarUmaListaDeSolicitacoes() {
		when(solicitacaoRepository.findAll()).thenReturn(List.of(solicitacao));

		List<SolicitacaoDTO> response = solicitacaoService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(SolicitacaoDTO.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNomeDonatario());
		assertEquals(Tipo.BERMUDA, response.get(0).getItem());
		assertEquals(QUANTIDADETOTAL, response.get(0).getQuantidadeItem());
		assertEquals(FILIAL, response.get(0).getFilialOng());
		assertEquals(LocalDate.now(), response.get(0).getDataCadastro());
	}

	@Test
	void deveriaRetornarUmaSolicitacaoAoBuscarPorId() {
		when(solicitacaoRepository.findById(anyInt())).thenReturn(solicitacaoOpt);

		SolicitacaoDTO response = solicitacaoService.findById(solicitacao.getId());

		assertNotNull(response);
		assertEquals(SolicitacaoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNomeDonatario());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(solicitacaoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			solicitacaoService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaDeletarUmaSolicitacaoComSucesso() {
		when(solicitacaoRepository.findById(anyInt())).thenReturn(solicitacaoOpt);
		Mockito.doNothing().when(solicitacaoRepository).deleteById(anyInt());
		solicitacaoService.deleteById(ID);

		verify(solicitacaoRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmDoador() {
		when(solicitacaoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			solicitacaoRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaCadastrarUmaSolicitacao() {
		when(ongRepository.findById(anyInt())).thenReturn(ongOpt);
		when(donatarioRepository.findById(anyInt())).thenReturn(donatarioOpt);
		when(itemService.atualizarItemPostSolicitacao(any())).thenReturn(item);

		SolicitacaoDTO response = solicitacaoService.save(solicitacaoFormDTO);

		assertNotNull(response);
		assertEquals(SolicitacaoDTO.class, response.getClass());
		assertEquals(NOME, response.getNomeDonatario());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	@Test
	void deveriaAtualizarUmaSolicitacaoComSucesso() {
		when(solicitacaoRepository.findById(anyInt())).thenReturn(solicitacaoOpt);
		when(ongRepository.findById(anyInt())).thenReturn(ongOpt);
		when(donatarioRepository.findById(anyInt())).thenReturn(donatarioOpt);
		when(itemService.atualizarItemPutSolicitacao(any(), any())).thenReturn(item);

		SolicitacaoDTO response = solicitacaoService.update(anyInt(), solicitacaoFormDTO);

		assertNotNull(response);
		assertEquals(SolicitacaoDTO.class, response.getClass());
		assertEquals(NOME, response.getNomeDonatario());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	private void iniciarSolicitacao() {

		ong = new ONG();
		ong.setId(ID);
		ong.setFilial(FILIAL);
		ongOpt = Optional.of(ong);

		donatario = new Donatario();
		donatario.setId(ID);
		donatario.setNome(NOME);
		donatario.setCpf(CPF);
		donatarioOpt = Optional.of(donatario);

		categoria = new Categoria();
		categoria.setId(ID);
		categoria.setNome(NOME);

		item = new Item();
		item.setCategoria(categoria);
		item.setId(ID);
		item.setTipo(Tipo.BERMUDA);
		item.setQuantidadeTotal(QUANTIDADETOTAL);

		solicitacao = new Solicitacao();
		solicitacao.setOng(ong);
		solicitacao.setDonatario(donatario);
		solicitacao.setId(ID);
		solicitacao.setItem(item);
		solicitacao.setDataCadastro(LocalDate.now());
		solicitacao.setQuantidade(QUANTIDADETOTAL);
		solicitacaoOpt = Optional.of(solicitacao);
		

		solicitacaoFormDTO = new SolicitacaoFormDTO();
		solicitacaoFormDTO.setId(ID);
		solicitacaoFormDTO.setId_donatario(ID);
		solicitacaoFormDTO.setId_ong(ID);
		solicitacaoFormDTO.setTipoItem(Tipo.BERMUDA);
		solicitacaoFormDTO.setQuantidadeItem(QUANTIDADETOTAL);

	}

}

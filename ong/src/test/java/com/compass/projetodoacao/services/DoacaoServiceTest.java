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

import com.compass.projetodoacao.client.TransporteClient;
import com.compass.projetodoacao.dto.DoacaoDTO;
import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.enums.Tipo;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class DoacaoServiceTest {
	@InjectMocks
	private DoacaoService doacaoService;

	@Mock
	private DoacaoRepository doacaoRepository;

	@Mock
	private ONGRepository ongRepository;

	@Mock
	private DoadorRepository doadorRepository;

	@Mock
	private ItemService itemService;
	
	@Mock
	private TransporteClient transporteClient;
	
	private DoacaoDTO doacaoDTO;
	
	
	private Categoria categoria;

	private Item item;

	private Doacao doacao;

	private DoacaoFormDTO doacaoFormDTO;

	private ONG ong;

	private Optional<Doacao> doacaoOpt;


	private Doador doador;
	private Optional<ONG> ongOpt;
	private Optional<Doador> doadorOpt;
	private static final Integer QUANTIDADETOTAL = 10;
	private static final Integer ID = 1;
	private static final String FILIAL = "Recife";
	private static final String NOME = "João";
	private static final String CPF = "12345678950";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarDoacao();
	}

	@Test
	void deveriaRetornarUmaListaDeDoacao() {
		when(doacaoRepository.findAll()).thenReturn(List.of(doacao));

		List<DoacaoDTO> response = doacaoService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(DoacaoDTO.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNomeDoador());
		assertEquals(Tipo.BERMUDA, response.get(0).getItem());
		assertEquals(QUANTIDADETOTAL, response.get(0).getQuantidadeItem());
		assertEquals(FILIAL, response.get(0).getFilialOng());
		assertEquals(LocalDate.now(), response.get(0).getDataCadastro());
	}

	@Test
	void deveriaRetornarUmaDoacaoAoBuscarPorId() {
		when(doacaoRepository.findById(anyInt())).thenReturn(doacaoOpt);

		DoacaoDTO response = doacaoService.findById(doacao.getId());

		assertNotNull(response);
		assertEquals(DoacaoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNomeDoador());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(doacaoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			doacaoService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaDeletarUmaSolicitacaoComSucesso() {
		when(doacaoRepository.findById(anyInt())).thenReturn(doacaoOpt);
		Mockito.doNothing().when(doacaoRepository).deleteById(anyInt());
		doacaoService.deleteById(ID);

		verify(doacaoRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmDoador() {
		when(doacaoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			doacaoRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaCadastrarUmaDoacao() {
		when(ongRepository.findById(anyInt())).thenReturn(ongOpt);
		when(doadorRepository.findById(anyInt())).thenReturn(doadorOpt);
		when(itemService.save(any())).thenReturn(item);

		
		DoacaoDTO response = doacaoService.save(doacaoFormDTO);

		assertNotNull(response);
		assertEquals(DoacaoDTO.class, response.getClass());
		assertEquals(NOME, response.getNomeDoador());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	@Test
	void deveriaAtualizarUmaDoacaoComSucesso() {
		when(doacaoRepository.findById(anyInt())).thenReturn(doacaoOpt);
		when(ongRepository.findById(anyInt())).thenReturn(ongOpt);
		when(doadorRepository.findById(anyInt())).thenReturn(doadorOpt);
		when(itemService.atualizarItemDoacao(any(), any())).thenReturn(item);

		DoacaoDTO response = doacaoService.update(anyInt(), doacaoFormDTO);

		assertNotNull(response);
		assertEquals(DoacaoDTO.class, response.getClass());
		assertEquals(NOME, response.getNomeDoador());
		assertEquals(Tipo.BERMUDA, response.getItem());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeItem());
		assertEquals(FILIAL, response.getFilialOng());
		assertEquals(LocalDate.now(), response.getDataCadastro());
	}

	private void iniciarDoacao() {

		ong = new ONG();
		ong.setId(ID);
		ong.setFilial(FILIAL);
		ongOpt = Optional.of(ong);

		doador = new Doador();
		doador.setId(ID);
		doador.setNome(NOME);
		doador.setCpf(CPF);
		doadorOpt = Optional.of(doador);

		categoria = new Categoria();
		categoria.setId(ID);
		categoria.setNome(NOME);

		item = new Item();
		item.setCategoria(categoria);
		item.setId(ID);
		item.setTipo(Tipo.BERMUDA);
		item.setQuantidadeTotal(QUANTIDADETOTAL);

		doacao = new Doacao();
		doacao.setOng(ong);
		doacao.setDoador(doador);
		doacao.setId(ID);
		doacao.setItem(item);
		doacao.setDataCadastro(LocalDate.now());
		doacao.setQuantidade(QUANTIDADETOTAL);
		doacaoOpt = Optional.of(doacao);
		

		doacaoFormDTO = new DoacaoFormDTO();
		doacaoFormDTO.setId(ID);
		doacaoFormDTO.setId_doador(ID);
		doacaoFormDTO.setId_ong(ID);
		doacaoFormDTO.setTipoItem(Tipo.BERMUDA);
		doacaoFormDTO.setQuantidadeItem(QUANTIDADETOTAL);

	}
	
}

package com.compass.projetodoacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.compass.projetodoacao.dto.DoadorDTO;
import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.DoadorPostFormDTO;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class DoadorServiceTest {
	
	@InjectMocks
	private DoadorService doadorService;
	
	@Mock
	private DoadorRepository doadorRepository;

	@Mock
	private TelefoneService telefoneService;

	@Mock
	private EnderecoService enderecoService;
	
	private Doador doador;
	
	private DoadorFormDTO  doadorFormDTO;
	
	private Optional<Doador> optionalDoador;
	
	private DoadorPostFormDTO doadorPostFormDTO;
	
	private static final Integer ID = 1;
	private static final String NOME = "João";
	private static final String CPF = "12345678950";
	private static final String LOGRADOURO = "Avenida Um";
	private static final String NUMERO = "123";
	private static final String COMPLEMENTO = "Sem Complemento";
	private static final String BAIRRO = "Janga";
	private static final String CIDADE = "Paulista";
	private static final String ESTADO = "PE";
	private static final String CEP = "53410530";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarDoador();
	}
	
	@Test
	void deveriaRetornarUmaListaDeDoadores() {
		when(doadorRepository.findAll()).thenReturn(List.of(doador));

		List<DoadorDTO> response = doadorService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(DoadorDTO.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(CPF, response.get(0).getCpf());
	}
	
	@Test
	void deveriaRetornarUmDoadorAoBuscarPorId() {
		when(doadorRepository.findById(anyInt())).thenReturn(optionalDoador);

		DoadorDTO response = doadorService.findById(doador.getId());

		assertNotNull(response);
		assertEquals(DoadorDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(doadorRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			doadorService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	@Test
	void deveriaDeletarUmDoadorComSucesso() {
		when(doadorRepository.findById(anyInt())).thenReturn(optionalDoador);
		Mockito.doNothing().when(doadorRepository).deleteById(anyInt());
		doadorService.deleteById(ID);

		verify(doadorRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmDoador() {
		when(doadorRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			doadorRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	@Test
	void deveriaCadastrarUmDoador() {
		when(doadorRepository.save(any())).thenReturn(doador);
		
		DoadorDTO response = doadorService.save(doadorPostFormDTO);

		assertNotNull(response);
		assertEquals(DoadorDTO.class, response.getClass());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}
	
	@Test
	void deveriaAtualizarUmDoadorComSucesso() {
		when(doadorRepository.findById(anyInt())).thenReturn(optionalDoador);

		DoadorDTO response = doadorService.update(anyInt(), doadorFormDTO);
		
		assertNotNull(response);
		assertEquals(DoadorDTO.class, response.getClass());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}

	private void iniciarDoador() {
		doador = new Doador();
		doador.setId(ID);
		doador.setNome(NOME);
		doador.setCpf(CPF);
		
		optionalDoador = Optional.of(doador);
		
		doadorFormDTO = new DoadorFormDTO();
		doadorFormDTO.setId(ID);
		doadorFormDTO.setNomeDoador(NOME);
		doadorFormDTO.setCpfDoador(CPF);
		
		doadorPostFormDTO = new DoadorPostFormDTO();
		doadorPostFormDTO.setId(ID);
		doadorPostFormDTO.setNomeDoador(NOME);
		doadorPostFormDTO.setCpfDoador(CPF);
		doadorPostFormDTO.setLogradouro(LOGRADOURO);
		doadorPostFormDTO.setNumero(NUMERO);
		doadorPostFormDTO.setComplemento(COMPLEMENTO);
		doadorPostFormDTO.setBairro(BAIRRO);
		doadorPostFormDTO.setCidade(CIDADE);
		doadorPostFormDTO.setEstado(ESTADO);
		doadorPostFormDTO.setCep(CEP);
	}
}

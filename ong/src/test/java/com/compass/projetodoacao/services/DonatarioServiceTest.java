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

import com.compass.projetodoacao.dto.DonatarioDTO;
import com.compass.projetodoacao.dto.DonatarioFormDTO;
import com.compass.projetodoacao.dto.DonatarioPostFormDTO;
import com.compass.projetodoacao.entities.Donatario;
import com.compass.projetodoacao.repositories.DonatarioRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class DonatarioServiceTest {
	
	@InjectMocks
	private DonatarioService donatarioService;
	
	@Mock
	private DonatarioRepository donatarioRepository;

	@Mock
	private TelefoneService telefoneService;

	@Mock
	private EnderecoService enderecoService;
	
	private Donatario donatario;
	
	private DonatarioFormDTO  donatarioFormDTO;
	
	private Optional<Donatario> optionalDonatario;
	
	private DonatarioPostFormDTO donatarioPostFormDTO;
	
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
		iniciarDonatario();
	}
	
	@Test
	void deveriaRetornarUmaListaDeDonatarios() {
		when(donatarioRepository.findAll()).thenReturn(List.of(donatario));

		List<DonatarioDTO> response = donatarioService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(DonatarioDTO.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(CPF, response.get(0).getCpf());
	}
	
	@Test
	void deveriaRetornarUmDonatarioAoBuscarPorId() {
		when(donatarioRepository.findById(anyInt())).thenReturn(optionalDonatario);

		DonatarioDTO response = donatarioService.findById(donatario.getId());

		assertNotNull(response);
		assertEquals(DonatarioDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(donatarioRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			donatarioService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	@Test
	void deveriaCadastrarUmDonatario() {
		when(donatarioRepository.save(any())).thenReturn(donatario);
		
		DonatarioDTO response = donatarioService.save(donatarioPostFormDTO);

		assertNotNull(response);
		assertEquals(DonatarioDTO.class, response.getClass());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}
	
	@Test
	void deveriaAtualizarUmDonatarioComSucesso() {
		when(donatarioRepository.findById(anyInt())).thenReturn(optionalDonatario);

		DonatarioDTO response = donatarioService.update(anyInt(), donatarioFormDTO);
		
		assertNotNull(response);
		assertEquals(DonatarioDTO.class, response.getClass());
		assertEquals(NOME, response.getNome());
		assertEquals(CPF, response.getCpf());
	}
	
	@Test
	void deveriaDeletarUmDonatarioComSucesso() {
		when(donatarioRepository.findById(anyInt())).thenReturn(optionalDonatario);
		Mockito.doNothing().when(donatarioRepository).deleteById(anyInt());
		donatarioService.deleteById(ID);

		verify(donatarioRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmDonatario() {
		when(donatarioRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			donatarioRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	private void iniciarDonatario() {
		donatario = new Donatario();
		donatario.setId(ID);
		donatario.setNome(NOME);
		donatario.setCpf(CPF);
		
		optionalDonatario = Optional.of(donatario);
		
		donatarioFormDTO = new DonatarioFormDTO();
		donatarioFormDTO.setId(ID);
		donatarioFormDTO.setNomeDonatario(NOME);
		donatarioFormDTO.setCpfDonatario(CPF);
		
		donatarioPostFormDTO = new DonatarioPostFormDTO();
		donatarioPostFormDTO.setId(ID);
		donatarioPostFormDTO.setNomeDonatario(NOME);
		donatarioPostFormDTO.setCpfDonatario(CPF);
		donatarioPostFormDTO.setLogradouro(LOGRADOURO);
		donatarioPostFormDTO.setNumero(NUMERO);
		donatarioPostFormDTO.setComplemento(COMPLEMENTO);
		donatarioPostFormDTO.setBairro(BAIRRO);
		donatarioPostFormDTO.setCidade(CIDADE);
		donatarioPostFormDTO.setEstado(ESTADO);
		donatarioPostFormDTO.setCep(CEP);
	}
}

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

import com.compass.projetodoacao.dto.EnderecoFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.repositories.EnderecoRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class EnderecoServiceTest {

	@InjectMocks
	private EnderecoService enderecoService;

	@Mock
	private EnderecoRepository enderecoRepository;

	private Endereco endereco;
	
	private EnderecoFormDTO enderecoDTO;

	private Optional<Endereco> optionalEndereco;

	private final static Integer ID = 1;
	private final static String LOGRADOURO = "Avenida Um";
	private final static String NUMERO = "123";
	private final static String COMPLEMENTO = "Sem Complemento";
	private final static String BAIRRO = "Janga";
	private final static String CIDADE = "Paulista";
	private final static String ESTADO = "PE";
	private final static String CEP = "53410530";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarEndereco();
	}
	
	@Test
	void deveriaRetornarUmTelefoneAoBuscarPorId() {
		when(enderecoRepository.findById(anyInt())).thenReturn(optionalEndereco);

		Endereco response = enderecoService.findById(endereco.getId());

		assertNotNull(response);
		assertEquals(Endereco.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(LOGRADOURO, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertEquals(CIDADE, response.getCidade());
		assertEquals(ESTADO, response.getEstado());
		assertEquals(CEP, response.getCep());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(enderecoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			enderecoService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaRetornarUmaListaDeTelefones() {
		when(enderecoRepository.findAll()).thenReturn(List.of(endereco));

		List<Endereco> response = enderecoService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Endereco.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(LOGRADOURO, response.get(0).getLogradouro());
		assertEquals(NUMERO, response.get(0).getNumero());
		assertEquals(COMPLEMENTO, response.get(0).getComplemento());
		assertEquals(BAIRRO, response.get(0).getBairro());
		assertEquals(CIDADE, response.get(0).getCidade());
		assertEquals(ESTADO, response.get(0).getEstado());
		assertEquals(CEP, response.get(0).getCep());
	}

	@Test
	void deveriaCriarUmTelefone() {
		when(enderecoRepository.save(any())).thenReturn(endereco);

		Endereco response = enderecoService.saveEndereco(enderecoDTO);

		assertNotNull(response);
		assertEquals(Endereco.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(LOGRADOURO, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertEquals(CIDADE, response.getCidade());
		assertEquals(ESTADO, response.getEstado());
		assertEquals(CEP, response.getCep());
	}
	
	@Test
	void deveriaAtualizarUmTelefoneComSucesso() {
		when(enderecoRepository.findById(any())).thenReturn(optionalEndereco);
		Endereco response = enderecoService.update(anyInt(), enderecoDTO);

		assertNotNull(response);
		assertEquals(Endereco.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(LOGRADOURO, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertEquals(CIDADE, response.getCidade());
		assertEquals(ESTADO, response.getEstado());
		assertEquals(CEP, response.getCep());
	}

	@Test
	void deveriaDeletarUmTelefoneComSucesso() {
		when(enderecoRepository.findById(anyInt())).thenReturn(optionalEndereco);
		Mockito.doNothing().when(enderecoService).deleteById(anyInt());
		enderecoService.deleteById(ID);

		verify(enderecoRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmTelefone() {
		when(enderecoRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			enderecoService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	private void iniciarEndereco() {
		endereco = new Endereco();
		endereco.setId(ID);
		endereco.setLogradouro(LOGRADOURO);
		endereco.setNumero(NUMERO);
		endereco.setComplemento(COMPLEMENTO);
		endereco.setBairro(BAIRRO);
		endereco.setCidade(CIDADE);
		endereco.setEstado(ESTADO);
		endereco.setCep(CEP);

		optionalEndereco = Optional.of(endereco);
		
		enderecoDTO = new EnderecoFormDTO(endereco);
	}
}

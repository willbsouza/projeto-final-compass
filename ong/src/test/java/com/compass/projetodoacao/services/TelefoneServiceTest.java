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

import com.compass.projetodoacao.dto.TelefoneFormDTO;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.TelefoneRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class TelefoneServiceTest {

	@InjectMocks
	private TransporteService telefoneService;

	@Mock
	private TelefoneRepository telefoneRepository;

	private Telefone telefone;
	
	private TelefoneFormDTO telefoneDTO;

	private Optional<Telefone> optionalTelefone;

	private static final Integer ID = 1;
	private static final String NUMERO = "81987654321";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarTelefone();
	}

	@Test
	void deveriaRetornarUmTelefoneAoBuscarPorId() {
		when(telefoneRepository.findById(anyInt())).thenReturn(optionalTelefone);

		Telefone response = telefoneService.findById(telefone.getId());

		assertNotNull(response);
		assertEquals(Telefone.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NUMERO, response.getNumero());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(telefoneRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));

		try {
			telefoneService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaRetornarUmaListaDeTelefones() {
		when(telefoneRepository.findAll()).thenReturn(List.of(telefone));

		List<Telefone> response = telefoneService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Telefone.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NUMERO, response.get(0).getNumero());
	}

	@Test
	void deveriaCriarUmTelefone() {
		when(telefoneRepository.save(any())).thenReturn(telefone);

		Telefone response = telefoneService.saveTelefone(telefoneDTO);

		assertNotNull(response);
		assertEquals(Telefone.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NUMERO, response.getNumero());
	}
	
	@Test
	void deveriaAtualizarUmTelefoneComSucesso() {
		when(telefoneRepository.findById(any())).thenReturn(optionalTelefone);
		Telefone response = telefoneService.update(anyInt(), telefoneDTO);

		assertNotNull(response);
		assertEquals(Telefone.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NUMERO, response.getNumero());
	}

	@Test
	void deveriaDeletarUmTelefoneComSucesso() {
		when(telefoneRepository.findById(anyInt())).thenReturn(optionalTelefone);
		Mockito.doNothing().when(telefoneRepository).deleteById(anyInt());
		telefoneService.deleteById(ID);

		verify(telefoneRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmTelefone() {
		when(telefoneRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			telefoneService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	private void iniciarTelefone() {
		telefone = new Telefone();
		telefone.setId(ID);
		telefone.setNumero(NUMERO);

		optionalTelefone = Optional.of(telefone);
		
		telefoneDTO = new TelefoneFormDTO();
		telefoneDTO.setNumero(NUMERO);
	}
}

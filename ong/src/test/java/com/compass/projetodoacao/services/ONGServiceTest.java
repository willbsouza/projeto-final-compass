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

import com.compass.projetodoacao.dto.ONGDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.dto.ONGPostFormDTO;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class ONGServiceTest {

	@InjectMocks
	private ONGService ongService;
	
	@Mock
	private ONGRepository ongRepository;

	@Mock
	private TelefoneService telefoneService;

	@Mock
	private EnderecoService enderecoService;
	
	private ONG ong;
	
	private ONGFormDTO ongFormDTO;
	
	private Optional<ONG> optionalONG;
	
	private ONGPostFormDTO ongPostFormDTO;
	
	private static final Integer ID = 1;
	private static final String FILIAL = "Filial Recife";
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
		iniciarONG();
	}
	
	@Test
	void deveriaRetornarUmaListaDeONGs() {
		when(ongRepository.findAll()).thenReturn(List.of(ong));

		List<ONGDTO> response = ongService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(ONGDTO.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(FILIAL, response.get(0).getFilial());
	}
	
	@Test
	void deveriaRetornarUmaONGAoBuscarPorId() {
		when(ongRepository.findById(anyInt())).thenReturn(optionalONG);

		ONGDTO response = ongService.findById(ong.getId());

		assertNotNull(response);
		assertEquals(ONGDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(FILIAL, response.getFilial());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(ongRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			ongService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	@Test
	void deveriaDeletarUmaONGComSucesso() {
		when(ongRepository.findById(anyInt())).thenReturn(optionalONG);
		Mockito.doNothing().when(ongRepository).deleteById(anyInt());
		ongService.deleteById(ID);

		verify(ongRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmaONG() {
		when(ongRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			ongRepository.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}
	
	@Test
	void deveriaCadastrarUmaONG() {
		when(ongRepository.save(any())).thenReturn(ong);
		
		ONGDTO response = ongService.save(ongPostFormDTO);

		assertNotNull(response);
		assertEquals(ONGDTO.class, response.getClass());
		assertEquals(FILIAL, response.getFilial());
	}
	
	@Test
	void deveriaAtualizarUmaONGComSucesso() {
		when(ongRepository.findById(anyInt())).thenReturn(optionalONG);

		ONGDTO response = ongService.update(anyInt(), ongFormDTO);
		
		assertNotNull(response);
		assertEquals(ONGDTO.class, response.getClass());
		assertEquals(FILIAL, response.getFilial());
	}
	
	private void iniciarONG() {
		
		ong = new ONG();
		ong.setId(ID);
		ong.setFilial(FILIAL);
		
		optionalONG = Optional.of(ong);
		
		ongFormDTO = new ONGFormDTO();
		ongFormDTO.setFilialONG(FILIAL);
		
		ongPostFormDTO = new ONGPostFormDTO();
		ongPostFormDTO.setFilialONG(FILIAL);
		ongPostFormDTO.setLogradouro(LOGRADOURO);
		ongPostFormDTO.setNumero(NUMERO);
		ongPostFormDTO.setComplemento(COMPLEMENTO);
		ongPostFormDTO.setBairro(BAIRRO);
		ongPostFormDTO.setCidade(CIDADE);
		ongPostFormDTO.setEstado(ESTADO);
		ongPostFormDTO.setCep(CEP);
	}

}

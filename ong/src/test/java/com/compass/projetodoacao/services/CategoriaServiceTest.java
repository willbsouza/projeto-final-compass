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

import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class CategoriaServiceTest {

	@InjectMocks
	private CategoriaService categoriaService;

	@Mock
	private CategoriaRepository categoriaRepository;

	private Categoria categoria;

	private Optional<Categoria> optionalCategoria;

	private static final Integer ID = 1;
	private static final String NOME = "Vestuário";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarCategoria();
	}

	@Test
	void deveriaRetornarUmaCategoriaAoBuscarPorId() {
		when(categoriaRepository.findById(anyInt())).thenReturn(optionalCategoria);

		Categoria response = categoriaService.findById(categoria.getId());

		assertNotNull(response);
		assertEquals(Categoria.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(categoriaRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));

		try {
			categoriaService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaRetornarUmaListaDeCategorias() {
		when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

		List<Categoria> response = categoriaService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Categoria.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
	}

	@Test
	void deveriaCriarUmaCategoria() {
		when(categoriaRepository.save(any())).thenReturn(categoria);

		Categoria response = categoriaService.save(categoria);

		assertNotNull(response);
		assertEquals(Categoria.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
	}
	
	@Test
	void deveriaAtualizarUmaCategoriaComSucesso() {
		when(categoriaRepository.findById(any())).thenReturn(optionalCategoria);
		Categoria obj = optionalCategoria.get();	
		Categoria response = categoriaService.update(anyInt(), obj);

		assertNotNull(response);
		assertEquals(Categoria.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
	}

	@Test
	void deveriaDeletarUmaCategoriaComSucesso() {
		when(categoriaRepository.findById(anyInt())).thenReturn(optionalCategoria);
		Mockito.doNothing().when(categoriaRepository).deleteById(anyInt());
		categoriaService.deleteById(ID);

		verify(categoriaRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundExceptionAoTentarDeletarUmaCategoria() {
		when(categoriaRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			categoriaService.deleteById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	private void iniciarCategoria() {
		categoria = new Categoria();
		categoria.setId(ID);
		categoria.setNome(NOME);

		optionalCategoria = Optional.of(categoria);
	}
}

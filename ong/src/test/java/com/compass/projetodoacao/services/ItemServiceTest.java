package com.compass.projetodoacao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.dto.ItemDTO;
import com.compass.projetodoacao.dto.ItemFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.repositories.ItemRepository;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@SpringBootTest
public class ItemServiceTest {

	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private CategoriaRepository categoriaRepository;
	
	private Item item;

	Optional<Item> itemOptional;
	
	private Categoria categoria;
	
	Optional<Categoria> categoriaOptional;
	
	private DoacaoFormDTO doacaoFormDTO;
	
	private ItemFormDTO itemFormDTO;
	
	private static final Integer ID = 1;
	private static final Integer QUANTIDADETOTAL = 20;
	private static final Integer QUANTIDADETOTALINCREMENTADA = 40;
	private static final String NOME = "Vestuário";
	private static final Tipo TIPO = Tipo.BERMUDA;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		iniciarItem();
	}
	
	@Test
	void deveriaRetornarUmaListaDeItens() {
		when(itemRepository.findAll()).thenReturn(List.of(item));

		List<ItemDTO> response = itemService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(ItemDTO.class, response.get(0).getClass());
		assertEquals(TIPO, response.get(0).getTipo());
		assertEquals(QUANTIDADETOTAL, response.get(0).getQuantidadeTotal());
	}

	@Test
	void deveriaRetornarUmaDoacaoAoBuscarPorId() {
		when(itemRepository.findById(anyInt())).thenReturn(itemOptional);

		ItemDTO response = itemService.findById(item.getId());

		assertNotNull(response);
		assertEquals(ItemDTO.class, response.getClass());
		assertEquals(TIPO, response.getTipo());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeTotal());
	}

	@Test
	void deveriaRetornarUmaObjectNotFoundException() {
		when(itemRepository.findById(anyInt()))
				.thenThrow(new ObjectNotFoundException("ID: " + ID + " não encontrado."));
		try {
			itemService.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("ID: " + ID + " não encontrado.", e.getMessage());
		}
	}

	@Test
	void deveriaCadastrarUmItem() {
		when(categoriaRepository.findById(anyInt())).thenReturn(categoriaOptional);
		when(itemRepository.findByTipo(any())).thenReturn(item);
	
		Item response = itemService.save(doacaoFormDTO);

		assertNotNull(response);
		assertEquals(Item.class, response.getClass());
		assertEquals(TIPO, response.getTipo());
		assertEquals(QUANTIDADETOTALINCREMENTADA, response.getQuantidadeTotal());
	}

	@Test
	void deveriaAtualizarUmItemComSucesso() {
		when(categoriaRepository.findById(anyInt())).thenReturn(categoriaOptional);
		when(itemRepository.findById(anyInt())).thenReturn(itemOptional);
		
		ItemDTO response = itemService.update(anyInt(), itemFormDTO);

		assertNotNull(response);
		assertEquals(ItemDTO.class, response.getClass());
		assertEquals(TIPO, response.getTipo());
		assertEquals(QUANTIDADETOTAL, response.getQuantidadeTotal());
	}
	
	private void iniciarItem() {
		
		categoria = new Categoria();
		categoria.setId(ID);
		categoria.setNome(NOME);
		
		categoriaOptional = Optional.of(categoria);

		item = new Item();
		item.setCategoria(categoria);
		item.setId(ID);
		item.setTipo(TIPO);
		item.setQuantidadeTotal(QUANTIDADETOTAL);
		
		itemOptional = Optional.of(item);
		
		itemFormDTO = new ItemFormDTO(item);
		
		doacaoFormDTO = new DoacaoFormDTO();
		doacaoFormDTO.setId(ID);
		doacaoFormDTO.setId_categoria(ID);
		doacaoFormDTO.setId_doador(ID);
		doacaoFormDTO.setId_ong(ID);
		doacaoFormDTO.setTipoItem(TIPO);
		doacaoFormDTO.setQuantidadeItem(QUANTIDADETOTAL);
	}
}

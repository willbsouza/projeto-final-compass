package com.compass.transportador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.repository.TransporteRepository;

@SpringBootTest
public class TransporteServiceTest {

		@InjectMocks
		private TransporteService transporteService;

		@Mock
		private TransporteRepository transporteRepository;

		private Transporte transporte;
		
		private static final Integer ID = 1;
		private static final Integer QUANTIDADE = 10;
		private static final String ENDERECO = "Av. Brasil, 123";
		private static final String ITEM = "Item";
		
		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
			iniciarTransporte();
		}

		@Test
		void deveriaRetornarUmTransporteAoBuscarPorIdDoacao() {
			when(transporteRepository.findByIdDoacao(anyInt())).thenReturn(transporte);

			Transporte response = transporteService.findByIdDoacao(ID);

			assertNotNull(response);
			assertEquals(Transporte.class, response.getClass());
			assertEquals(ID, response.getId());
			assertEquals(ID, response.getIdDoacao());
			assertEquals(ENDERECO, response.getEnderecoDestino());
			assertEquals(ENDERECO, response.getEnderecoOrigem());
			assertEquals(LocalDate.now(), response.getDataPedido());
			assertEquals(LocalDate.now(), response.getDataPrevisaoServico());
			assertEquals(QUANTIDADE, response.getQuantidade());
			assertEquals(ITEM, response.getItem());
		}

		@Test
		void deveriaRetornarUmaListaDeTransportes() {
			when(transporteRepository.findAll()).thenReturn(List.of(transporte));

			List<Transporte> response = transporteService.findAll();

			assertNotNull(response);
			assertEquals(ID, response.get(0).getId());
			assertEquals(ID, response.get(0).getIdDoacao());
			assertEquals(ENDERECO, response.get(0).getEnderecoDestino());
			assertEquals(ENDERECO, response.get(0).getEnderecoOrigem());
			assertEquals(LocalDate.now(), response.get(0).getDataPedido());
			assertEquals(LocalDate.now(), response.get(0).getDataPrevisaoServico());
			assertEquals(QUANTIDADE, response.get(0).getQuantidade());
			assertEquals(ITEM, response.get(0).getItem());
		}

		@Test
		void deveriaCriarUmTransporte() {
			when(transporteRepository.save(any())).thenReturn(transporte);

			Transporte response = transporteService.save(transporte);

			assertNotNull(response);
			assertEquals(Transporte.class, response.getClass());
			assertEquals(ID, response.getId());
			assertEquals(ID, response.getIdDoacao());
			assertEquals(ENDERECO, response.getEnderecoDestino());
			assertEquals(ENDERECO, response.getEnderecoOrigem());
			assertEquals(LocalDate.now(), response.getDataPedido());
			assertEquals(LocalDate.now(), response.getDataPrevisaoServico());
			assertEquals(QUANTIDADE, response.getQuantidade());
			assertEquals(ITEM, response.getItem());
		}
		
		@Test
		void deveriaAtualizarUmTransporteComSucesso() {
			when(transporteRepository.findByIdDoacao(any())).thenReturn(transporte);
			Transporte response = transporteService.update(anyInt(), transporte);

			assertNotNull(response);
			assertEquals(Transporte.class, response.getClass());
			assertEquals(ID, response.getId());
			assertEquals(ID, response.getIdDoacao());
			assertEquals(ENDERECO, response.getEnderecoDestino());
			assertEquals(ENDERECO, response.getEnderecoOrigem());
			assertEquals(LocalDate.now(), response.getDataPedido());
			assertEquals(LocalDate.now(), response.getDataPrevisaoServico());
			assertEquals(QUANTIDADE, response.getQuantidade());
			assertEquals(ITEM, response.getItem());
		}

		@Test
		void deveriaDeletarUmTransporteComSucesso() {
			Mockito.doNothing().when(transporteRepository).deleteByIdDoacao(ID);
			transporteService.deleteByIdDoacao(ID);

			verify(transporteRepository, times(1)).deleteByIdDoacao(anyInt());
		}

		private void iniciarTransporte() {

			transporte = new Transporte();
			transporte.setId(ID);
			transporte.setEnderecoOrigem(ENDERECO);
			transporte.setEnderecoDestino(ENDERECO);
			transporte.setItem(ITEM);
			transporte.setQuantidade(QUANTIDADE);
			transporte.setDataPedido(LocalDate.now());
			transporte.setDataPrevisaoServico(LocalDate.now());
			transporte.setIdDoacao(ID);
		}
	}


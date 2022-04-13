package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.dto.ItemDTO;
import com.compass.projetodoacao.dto.ItemFormDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.Solicitacao;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.repositories.ItemRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
import com.compass.projetodoacao.services.exception.InvalidQuantityException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<ItemDTO> findAll() {
		List<Item> listItem = itemRepository.findAll();
		return listItem.stream().map(item -> new ItemDTO(item)).collect(Collectors.toList());
	}

	public ItemDTO findById(Integer id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new ItemDTO(item);
	}

	public Item save(@Valid DoacaoFormDTO doacaoDTO) {

		Categoria categoria = categoriaRepository.findById(doacaoDTO.getId_categoria()).orElseThrow(
				() -> new ObjectNotFoundException("Categoria ID: " + doacaoDTO.getId_categoria() + " não encontrado."));
		Item item = itemRepository.findByTipo(doacaoDTO.getTipoItem());
		try {
			if (doacaoDTO.getQuantidadeItem() < 1) {
				throw new InvalidQuantityException("Quantidade menor que 1.");
			}
			if (item != null) {
				item.setQuantidadeTotal(item.getQuantidadeTotal() + doacaoDTO.getQuantidadeItem());
				return item;
			} else {
				Item itemNovo = new Item();
				itemNovo.setQuantidadeTotal(doacaoDTO.getQuantidadeItem());
				itemNovo.setTipo(doacaoDTO.getTipoItem());
				itemNovo.setCategoria(categoria);
				itemRepository.save(itemNovo);
				return itemNovo;
			}
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public ItemDTO update(Integer id, @Valid ItemFormDTO itemFormDTO) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		Categoria categoria = categoriaRepository.findById(itemFormDTO.getIdCategoria())
				.orElseThrow(() -> new ObjectNotFoundException(
						"Categoria ID: " + itemFormDTO.getIdCategoria() + " não encontrado."));
		if (itemFormDTO.getQuantidadeTotal() < 0) {
			throw new InvalidQuantityException("Quantidade menor que 0.");
		}
		if (item != null) {
			item.setQuantidadeTotal(itemFormDTO.getQuantidadeTotal());
			item.setTipo(itemFormDTO.getTipo());
			item.setCategoria(categoria);
		}
		return new ItemDTO(item);
	}

	public Item atualizarItemDoacao(Doacao doacaoAnterior, DoacaoFormDTO doacaoAtualizada) {

		Categoria categoria = categoriaRepository.findById(doacaoAtualizada.getId_categoria())
				.orElseThrow(() -> new ObjectNotFoundException(
						"Categoria ID: " + doacaoAtualizada.getId_categoria() + " não encontrado."));
		Item itemAntigo = itemRepository.findByTipo(doacaoAnterior.getItem().getTipo());
		Item itemNovo = itemRepository.findByTipo(doacaoAtualizada.getTipoItem());
		itemAntigo.setQuantidadeTotal(itemAntigo.getQuantidadeTotal() - doacaoAnterior.getQuantidade());
		if (itemNovo == null) {
			return save(doacaoAtualizada);
		}
		itemNovo.setQuantidadeTotal(itemNovo.getQuantidadeTotal() + doacaoAtualizada.getQuantidadeItem());
		itemNovo.setCategoria(categoria);
		return itemNovo;
	}

	public Item atualizarItemPostSolicitacao(SolicitacaoFormDTO solicitacaoDTO) {

		Item item = itemRepository.findByTipo(solicitacaoDTO.getTipoItem());
		if (item == null) {
			throw new ObjectNotFoundException("Item não encontrado.");
		}
		if (solicitacaoDTO.getQuantidadeItem() < 1 || solicitacaoDTO.getQuantidadeItem() > 3) {
			throw new InvalidQuantityException(
					"Quantidade: " + solicitacaoDTO.getQuantidadeItem() + " é inválida. Solicite entre 1 a 3 peças");
		}
		if (solicitacaoDTO.getQuantidadeItem() > item.getQuantidadeTotal()) {
			throw new InvalidQuantityException("Quantidade indisponível no estoque do item requerido.");
		}
		item.setQuantidadeTotal(item.getQuantidadeTotal() - solicitacaoDTO.getQuantidadeItem());
		return item;
	}

	public Item atualizarItemPutSolicitacao(Solicitacao solicitacaoAnterior, SolicitacaoFormDTO solicitacaoAtualizada) {
		Item itemNovo = itemRepository.findByTipo(solicitacaoAtualizada.getTipoItem());
		Item itemAntigo = itemRepository.findByTipo(solicitacaoAnterior.getItem().getTipo());
		if (itemNovo == null) {
			throw new ObjectNotFoundException("Item não encontrado.");
		}
		if (solicitacaoAtualizada.getQuantidadeItem() < 1 || solicitacaoAtualizada.getQuantidadeItem() > 3) {
			throw new InvalidQuantityException("Quantidade: " + solicitacaoAtualizada.getQuantidadeItem()
					+ " é inválida. Solicite entre 1 a 3 peças");
		}
		if (solicitacaoAtualizada.getQuantidadeItem() > itemNovo.getQuantidadeTotal()) {
			throw new InvalidQuantityException("Quantidade indisponível no estoque do item requerido.");
		}
		itemAntigo.setQuantidadeTotal(itemAntigo.getQuantidadeTotal() + solicitacaoAnterior.getQuantidade());
		itemNovo.setQuantidadeTotal(itemNovo.getQuantidadeTotal() - solicitacaoAtualizada.getQuantidadeItem());
		return itemNovo;
	}

	public void atualizaItemDeleteDoacao(Doacao doacao) {
		Item item = itemRepository.findByTipo(doacao.getItem().getTipo());
		if (doacao.getQuantidade() <= item.getQuantidadeTotal()) {
			item.setQuantidadeTotal(item.getQuantidadeTotal() - doacao.getQuantidade());
		} else {
			throw new InvalidQuantityException("Não é possível excluir doação. "
					+ "Estoque com quantidade inferior a quantidade doada. Realize uma atualização no item!");
		}
	}
	
	public void atualizaItemDeleteSolicitacao(Solicitacao solicitacao) {
		Item item = itemRepository.findByTipo(solicitacao.getItem().getTipo());
		item.setQuantidadeTotal(item.getQuantidadeTotal() + solicitacao.getQuantidade());
	}
}

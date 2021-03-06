package com.compass.projetodoacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	public Item findByTipo(Tipo tipoItem);

}

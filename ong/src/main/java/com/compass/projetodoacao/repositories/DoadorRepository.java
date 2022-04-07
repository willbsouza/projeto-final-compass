package com.compass.projetodoacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.projetodoacao.entities.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Integer>{

}

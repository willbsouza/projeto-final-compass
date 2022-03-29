package com.compass.projetodoacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.projetodoacao.entities.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer>{

}

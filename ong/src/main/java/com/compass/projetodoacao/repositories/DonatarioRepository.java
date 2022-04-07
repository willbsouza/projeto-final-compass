package com.compass.projetodoacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.projetodoacao.entities.Donatario;

@Repository
public interface DonatarioRepository extends JpaRepository<Donatario, Integer>{

}

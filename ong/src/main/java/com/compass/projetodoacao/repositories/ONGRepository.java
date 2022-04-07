package com.compass.projetodoacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.projetodoacao.entities.ONG;

@Repository
public interface ONGRepository extends JpaRepository<ONG, Integer>{
}

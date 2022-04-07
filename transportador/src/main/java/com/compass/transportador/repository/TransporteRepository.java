package com.compass.transportador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.transportador.entity.Transporte;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Integer>{
	
}

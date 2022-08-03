package com.juegodados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juegodados.entities.Jugador;

public interface IJugadorRepository extends JpaRepository<Jugador, Integer> {

}


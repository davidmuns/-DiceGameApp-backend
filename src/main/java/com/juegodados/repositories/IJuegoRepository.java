package com.juegodados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juegodados.entities.Juego;

public interface IJuegoRepository extends JpaRepository<Juego, Integer> {

	List<Juego> findAllByIdJugador(int idJugador);

	void deleteAllByIdJugador(int idJugador);
}

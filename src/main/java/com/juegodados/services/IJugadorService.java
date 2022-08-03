package com.juegodados.services;

import java.util.List;
import java.util.Optional;

import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Jugador;

public interface IJugadorService {
	public List<JugadorDto> getAllJugadores();

	public JugadorDto guardarJugador(Jugador jugador);

	public Optional<Jugador> buscarPorIdJugador(int idJugador);

	public void eliminarJugador(Jugador jugador);

}

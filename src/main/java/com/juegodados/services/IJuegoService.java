package com.juegodados.services;

import java.util.List;

import com.juegodados.dto.JuegoDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;

public interface IJuegoService {
	
	
	public JuegoDto guardarNuevoJuego(Juego nuevoJuego, Jugador jugador);

	
	
	public List<Juego> getAllJuegos();

	
	
	public List<JuegoDto> getAllJuegosJugador(Jugador jugador);

	
	
	public void eliminarAllJuegosJugador(Jugador jugador);
}

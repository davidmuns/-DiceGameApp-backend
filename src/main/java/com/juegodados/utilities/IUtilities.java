package com.juegodados.utilities;

import java.util.List;
import java.util.function.Predicate;

import com.juegodados.dto.JuegoDto;
import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;

public interface IUtilities {

	public boolean nombreJugadorExiste(List<JugadorDto> jugadores, Predicate<JugadorDto> predicate);

	public Jugador setAnonimoIfNameNullorBlank(Jugador nuevoJugador);

	public Double calcularRatioJugador(List<JuegoDto> juegosJugador);

	public double getPorcentajeTotalVictorias(List<Juego> juegos);

	public Juego getNuevoJuego(int idJugador);
	
	public List<JugadorDto> getRankingOrdenadoByRatio(List<JugadorDto> jugadoresDto);
}

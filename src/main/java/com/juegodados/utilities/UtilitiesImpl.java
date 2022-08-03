package com.juegodados.utilities;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.juegodados.dto.JuegoDto;
import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;

@Component
public class UtilitiesImpl implements IUtilities {
	
	private DecimalFormat df = new DecimalFormat("####.##");

	@Override
	public boolean nombreJugadorExiste(List<JugadorDto> jugadores, Predicate<JugadorDto> predicate) {
		boolean existe = false;
		for (JugadorDto jugador : jugadores) {
			if (predicate.test(jugador))
				existe = true;
		}
		return existe;
	}

	@Override
	public Jugador setAnonimoIfNameNullorBlank(Jugador nuevoJugador) {

		if (nuevoJugador.getNombre() == null || nuevoJugador.getNombre().isBlank())
			nuevoJugador.setNombre("anonimo");

		nuevoJugador.setNombre(nuevoJugador.getNombre());

		return nuevoJugador;
	}
	
	
	public Juego getNuevoJuego(int idJugador) {
		Juego juego = new Juego();
		int caraDado1 = (int) (Math.random() * 6) + 1;
		int caraDado2 = (int) (Math.random() * 6) + 1;
		juego.setIdJugador(idJugador);
		juego.setDado1(caraDado1);
		juego.setDado2(caraDado2);
		juego.setResultado(juego.getDado1() + juego.getDado2());

		return juego;
	}

	@Override
	public Double calcularRatioJugador(List<JuegoDto> juegosJugador) {
		double victorias = 0.0;
		double partidas = (double) juegosJugador.size();
		for (JuegoDto juego : juegosJugador) {
			if (juego.getResultado() == 7) {
				victorias++;
			}
		}	
		Double ratio = (victorias / partidas) * 100;
		
		return ratio;
	}
	
	@Override
	public double getPorcentajeTotalVictorias(List<Juego> juegos) {
		double victorias = 0.0;
		double porcentajeTotalVictorias = 0.0;
		double partidas = (double) juegos.size();

		for (Juego juego : juegos) {
			if (juego.getResultado() == 7) {
				victorias++;
			}
		}

		if (partidas == 0.0)
			porcentajeTotalVictorias = 0.0;
		else
			porcentajeTotalVictorias = (victorias / partidas) * 100;

		return porcentajeTotalVictorias;

	}
	
	@Override
	public List<JugadorDto> getRankingOrdenadoByRatio(List<JugadorDto> jugadoresDto) {
		//Collections.sort(jugadoresDto, (j1, j2) -> j1.getRatio().compareTo(j2.getRatio()));
		return	jugadoresDto.stream()
					.sorted(Comparator.comparingDouble(JugadorDto::getRatio).reversed())
					.collect(Collectors.toList());
	}


}

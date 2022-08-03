package com.juegodados.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.juegodados.dto.JuegoDto;
import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilitiesImplTest {
	
	@Autowired
	private UtilitiesImpl utilitiesImpl;
	
	@Test
	void nombreJugadorExisteTest() {
		JugadorDto jugador1 = new JugadorDto();
		jugador1.setNombre("David");
		
		List<JugadorDto> dtos = new ArrayList<>();
		dtos.add(jugador1);
		
		JugadorDto nuevoJugador = new JugadorDto();
		nuevoJugador.setNombre("David");
		
		Predicate<JugadorDto> condition = jugadorDto -> 
			jugadorDto.getNombre().equalsIgnoreCase(nuevoJugador.getNombre())
				&& !nuevoJugador.getNombre().equalsIgnoreCase("anonimo");
		
		boolean actual = utilitiesImpl.nombreJugadorExiste(dtos, condition);
		assertEquals(true, actual);
		
	}
	
	@Test
	void setAnonimoIfNameNullorBlankTest() {
		String expectedName = "anonimo";
		
		Jugador jugadorNullName = new Jugador();
		jugadorNullName.setNombre(null);
		
		Jugador jugadorBlankName = new Jugador();
		jugadorBlankName.setNombre("");
		
		String nullName  = utilitiesImpl.setAnonimoIfNameNullorBlank(jugadorNullName).getNombre();
		String blankName  = utilitiesImpl.setAnonimoIfNameNullorBlank(jugadorBlankName).getNombre();
		
		assertEquals(expectedName, nullName);
		assertEquals(expectedName, blankName);
		
	}
	
	@Test
	void calcularRatioJugadorTest() {
			
		JuegoDto juego1 = new JuegoDto();
		juego1.setResultado(7);
		
		JuegoDto juego2 = new JuegoDto();
		juego2.setResultado(7);
		
		JuegoDto juego3 = new JuegoDto();
		juego3.setResultado(3);
		
		double victorias = 2.0;
		
		
		List<JuegoDto> juegos = new ArrayList<>();
		juegos.add(juego1);
		juegos.add(juego2);
		juegos.add(juego3);
		
		double partidas = (double)juegos.size();
		
		Double expected = (victorias / partidas) * 100;
			
		Double actual = utilitiesImpl.calcularRatioJugador(juegos);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void getPorcentajeTotalVictoriasTest() {
		
		List<Juego> juegos = new ArrayList<>();
		List<Juego> juegos2 = new ArrayList<>();
		
		Juego juego1 = new Juego();
		juego1.setResultado(7);
	
		Juego juego2 = new Juego();
		juego2.setResultado(7);
		
		Juego juego3 = new Juego();
		juego3.setResultado(3);
		
		juegos.add(juego1);
		juegos.add(juego2);
		juegos.add(juego3);
		
		double expected = (2.0 / 3.0) * 100; 
	
		double actual = utilitiesImpl.getPorcentajeTotalVictorias(juegos);
		double actual2 = utilitiesImpl.getPorcentajeTotalVictorias(juegos2);
		
		assertEquals(expected, actual);
		assertEquals(0.0, actual2);
	}
	
	@Test
	void getRankingOrdenadoByRatioTest() {
		JugadorDto jugador1 = new JugadorDto();
		jugador1.setNombre("David");
		jugador1.setRatio(50.0);
		
		JugadorDto jugador2 = new JugadorDto();
		jugador2.setNombre("Jonatan");
		jugador2.setRatio(10.0);
		
		List<JugadorDto> dtos = new ArrayList<>();
		dtos.add(jugador1);
		dtos.add(jugador2);
		
		String actual = utilitiesImpl.getRankingOrdenadoByRatio(dtos).get(0).getNombre();
		
		assertEquals(jugador1.getNombre(), actual);
	}

}

package com.juegodados.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Jugador;
import com.juegodados.repositories.IJugadorRepository;

@Service
public class JugadorServicImpl implements IJugadorService {

	@Autowired
	IJugadorRepository iJugadorRepository;

	public List<JugadorDto> getAllJugadores() {
		
		//Pageable pageable = PageRequest.of(0, 2);	
		//Page<Jugador> jugadoresPaged = iJugadorRepository.findAll(pageable);
		//List<Jugador> jugadores = jugadoresPaged.getContent();
		
		List<Jugador> jugadores = iJugadorRepository.findAll();
		
		List<JugadorDto> jugadoresDto = new ArrayList<JugadorDto>();
		
		for(Jugador jugador: jugadores) {
			JugadorDto jugadorDto = new JugadorDto();
			jugadorDto.setIdJugador(jugador.getIdJugador());
			jugadorDto.setNombre(jugador.getNombre());
			jugadorDto.setRatio(jugador.getRatio());
			jugadoresDto.add(jugadorDto);
		}
		return jugadoresDto;
	}

	public JugadorDto guardarJugador(Jugador jugador) {
	
		iJugadorRepository.save(jugador);
		
		JugadorDto jugadorDto = new JugadorDto();
		
		jugadorDto.setIdJugador(jugador.getIdJugador());
		jugadorDto.setNombre(jugador.getNombre());
		jugadorDto.setRatio(jugador.getRatio());
		
		return jugadorDto;
	}

	public Optional<Jugador> buscarPorIdJugador(int idJugador) {
		return iJugadorRepository.findById(idJugador);
	}

	public void eliminarJugador(Jugador jugador) {
		iJugadorRepository.delete(jugador);
	}

}

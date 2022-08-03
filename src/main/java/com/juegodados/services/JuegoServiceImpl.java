package com.juegodados.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.juegodados.dto.JuegoDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;
import com.juegodados.repositories.IJuegoRepository;

@Service
public class JuegoServiceImpl implements IJuegoService {

	@Autowired
	private IJuegoRepository iJuegoRepository;

	@Autowired
	private JuegoDto juegoDto;

	@Override
	public List<JuegoDto> getAllJuegosJugador(Jugador jugador) {

		List<Juego> juegos = iJuegoRepository.findAllByIdJugador(jugador.getIdJugador());
		
		List<JuegoDto> juegosDto = new ArrayList<JuegoDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		for (Juego juego : juegos) {
			
			JuegoDto juegoDto = modelMapper.map(juego, JuegoDto.class);
			
			juegoDto.setNombreJugador(jugador.getNombre());
			
			juegoDto.setVictoria(juegoDto.getResultado() == 7 ? true : false);
			
			juegosDto.add(juegoDto);
		}
		return juegosDto;
	}

	@Override
	public void eliminarAllJuegosJugador(Jugador jugador) {
		
		List<Juego> juegos = iJuegoRepository.findAllByIdJugador(jugador.getIdJugador());
		
		iJuegoRepository.deleteInBatch(juegos);

	}

	@Override
	public JuegoDto guardarNuevoJuego(Juego nuevoJuego, Jugador jugador) {

		iJuegoRepository.save(nuevoJuego);

		ModelMapper modelMapper = new ModelMapper();

		String nombre = jugador.getNombre();

		juegoDto = modelMapper.map(nuevoJuego, JuegoDto.class);

		juegoDto.setNombreJugador(nombre);

		juegoDto.setVictoria(juegoDto.getResultado() == 7 ? true : false);

		return juegoDto;
	}

	@Override
	public List<Juego> getAllJuegos() {
		return iJuegoRepository.findAll();
	}

}

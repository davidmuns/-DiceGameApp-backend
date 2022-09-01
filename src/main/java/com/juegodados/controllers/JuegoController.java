package com.juegodados.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juegodados.dto.JuegoDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;
import com.juegodados.services.IJuegoService;
import com.juegodados.services.IJugadorService;
import com.juegodados.utilities.IUtilities;

@RestController
@RequestMapping("/juegodados/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class JuegoController {

    @Autowired
    private IJuegoService iJuegoService;

    @Autowired
    private IJugadorService iJugadorService;

    @Autowired
    private IUtilities iUtilities;

    // NUEVA JUGADA
    @PostMapping("jugada/add/{idJugador}")
    public ResponseEntity<?> jugar(@PathVariable("idJugador") int idJugador) {

	Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

	if (optionalJugador.isPresent()) {
	    Jugador jugador = optionalJugador.get();

	    Juego nuevoJuego = iUtilities.getNuevoJuego(idJugador);

	    JuegoDto juegoDto = iJuegoService.guardarNuevoJuego(nuevoJuego, jugador);

	    List<JuegoDto> juegosDto = iJuegoService.getAllJuegosJugador(jugador);

	    Double ratioJugador = iUtilities.calcularRatioJugador(juegosDto);

	    jugador.setRatio(ratioJugador);

	    iJugadorService.guardarJugador(jugador);

	    return ResponseEntity.ok(juegoDto);
	}

	return ResponseEntity.status(404)
		.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

    }

    // JUGADAS POR JUGADOR
    @GetMapping("jugada/all/{idJugador}")
    public ResponseEntity<?> getJugadasUnJugador(@PathVariable("idJugador") int idJugador) {

	Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

	if (optionalJugador.isPresent()) {

	    Jugador jugador = optionalJugador.get();

	    List<JuegoDto> juegosDto = iJuegoService.getAllJuegosJugador(jugador);

	    return ResponseEntity.ok(juegosDto);
	}
	return ResponseEntity.status(404)
		.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

    }

    // BORRAR TODAS LAS JUGADAS DE UN JUGADOR
    @DeleteMapping("jugada/delete/all/{idJugador}")
    public ResponseEntity<?> eliminarJugadasUnJugador(@PathVariable("idJugador") int idJugador) {

	Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

	if (optionalJugador.isPresent()) {
	    Jugador jugador = optionalJugador.get();

	    iJuegoService.eliminarAllJuegosJugador(jugador);

	    jugador.setRatio(0.0);

	    iJugadorService.guardarJugador(jugador);

	    return ResponseEntity.ok(jugador);

	} else {
	    return ResponseEntity.status(404)
		    .body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

	}

    }
}

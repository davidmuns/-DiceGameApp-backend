package com.juegodados.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juegodados.dto.JugadorDto;
import com.juegodados.entities.Juego;
import com.juegodados.entities.Jugador;
import com.juegodados.services.IJuegoService;
import com.juegodados.services.IJugadorService;
import com.juegodados.utilities.IUtilities;

@RestController
@RequestMapping(value = "/juegodados/api/v1")
public class JugadorController {

	@Autowired
	private IJuegoService iJuegoService;

	@Autowired
	private IJugadorService iJugadorService;

	@Autowired
	private IUtilities iUtilities;

	private DecimalFormat df = new DecimalFormat("####.##");

	// OBTENER LISTADO DE TODOS LOS JUGADORES
	@GetMapping(value = "/jugador/all")
	public ResponseEntity<?> getJugadores() 
	{
		List<JugadorDto> jugadoresDto = iJugadorService.getAllJugadores();
		
		return ResponseEntity.ok(jugadoresDto);
	}

	// CREAR NUEVO JUGADOR
	@PostMapping(value = "/jugador/add")
	public ResponseEntity<?> crearJugador(@RequestBody Jugador nuevoJugador) {

		List<JugadorDto> jugadoresDto = iJugadorService.getAllJugadores();

		boolean existe = iUtilities.nombreJugadorExiste(jugadoresDto,
				jugadorDto-> jugadorDto.getNombre().equalsIgnoreCase(nuevoJugador.getNombre())
						&& !jugadorDto.getNombre().equalsIgnoreCase("anonimo"));

		
		if (existe) { 
		 
		 return ResponseEntity
				 .badRequest()
				 .body("Ya existe un jugador llamado " + nuevoJugador.getNombre()); 
		
		}
		 
		
		Jugador jugador = iUtilities.setAnonimoIfNameNullorBlank(nuevoJugador);
		
		JugadorDto jugadorDto = iJugadorService.guardarJugador(jugador);

		return ResponseEntity.ok(jugadorDto);
	}

	// MODIFICAR NOMBRE JUGADOR
	@PutMapping(value = "/jugador/update/{idJugador}")
	public ResponseEntity<?> modificarNombreJugador(@PathVariable("idJugador") int idJugador,
			@RequestBody Jugador jugador) {

		if (jugador.getNombre() == null)
			jugador.setNombre("");

		List<JugadorDto> jugadoresDto = iJugadorService.getAllJugadores();

		boolean existe = iUtilities.nombreJugadorExiste(jugadoresDto,
				jugadorDto -> jugadorDto.getNombre().equalsIgnoreCase(jugador.getNombre()));

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent() && existe == false && !jugador.getNombre().isBlank()) {

			Jugador actualizarJugador = optionalJugador.get();
			actualizarJugador.setNombre(jugador.getNombre());
			
			JugadorDto jugadorDto = iJugadorService.guardarJugador(actualizarJugador);
			
			return ResponseEntity.ok(jugadorDto);
			//return ResponseEntity.ok("Jugador actualizado. Nombre nuevo: " + actualizarJugador.getNombre());
		}

		if (optionalJugador.isEmpty())
			return ResponseEntity.status(404)
					.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

		if (existe)
			return ResponseEntity.status(404).body(
					"Ya existe un jugador llamado " + jugador.getNombre() + ". Consultar el listado de jugadores.");

		if (jugador.getNombre().isBlank())
			return ResponseEntity.status(404).body("El campo nombre es obligatorio.");

		return null;
	}

	// ELIMINAR UN JUGADOR
	@DeleteMapping("/jugador/delete/{idJugador}")
	public ResponseEntity<?> eliminarUnJugador(@PathVariable("idJugador") int idJugador) {

		Optional<Jugador> optionalJugador = iJugadorService.buscarPorIdJugador(idJugador);

		if (optionalJugador.isPresent()) {
			iJugadorService.eliminarJugador(optionalJugador.get());
			return ResponseEntity.ok("Eliminado jugador " + optionalJugador.get().getNombre() + " con id " + idJugador);
		}
		return ResponseEntity.status(404)
				.body("El jugador con id " + idJugador + " no existe. Consultar el listado de jugadores.");

	}
	// OBTENER PROCENTAJE TOTAL DE VICTORIAS
	@GetMapping("/ratio-total-victorias")
	public ResponseEntity<?> porcentajeVictorias() {

		List<Juego> juegos = iJuegoService.getAllJuegos();

		Double porcentajeTotalVictorias = iUtilities.getPorcentajeTotalVictorias(juegos);
		
		//return ResponseEntity.ok(porcentajeTotalVictorias);
		return ResponseEntity.ok("Total partidas jugadas: " + juegos.size() + "\nTotal victorias: "
				+ (int) ((juegos.size()) * (porcentajeTotalVictorias / 100)) + "\nPorcentaje victorias: "
				+ df.format(porcentajeTotalVictorias) + " %");

	}

	// OBTENER JUGADOR CON PEOR PORCENTAJE DE VICTORIAS
	@GetMapping("ranking/peor")
	public ResponseEntity<?> peorJugador() {

		List<JugadorDto> jugadoresDto = iJugadorService.getAllJugadores();

		JugadorDto peorJugadorDto;

		if (jugadoresDto.size() != 0) {
			peorJugadorDto = iUtilities.getRankingOrdenadoByRatio(jugadoresDto).get(jugadoresDto.size() - 1);
			
			return ResponseEntity.ok(peorJugadorDto);
			//return ResponseEntity.ok("id: " + peorJugador.getIdJugador() + "\nNombre: " + peorJugador.getNombre()
					//+ "\nRatio: " + df.format(peorJugador.getRatio()) + " %");
		}
		return ResponseEntity.status(404).body("No hay jugadores en la lista.");

	}

	// OBTENER JUGADOR CON MEJOR PORCENTAJE DE VICTORIAS
	@GetMapping("ranking/mejor")
	public ResponseEntity<?> mejorJugador() {

		List<JugadorDto> jugadoresDto = iJugadorService.getAllJugadores();

		JugadorDto mejorJugadorDto;

		if (jugadoresDto.size() != 0) {
			mejorJugadorDto = iUtilities.getRankingOrdenadoByRatio(jugadoresDto).get(0);
			return ResponseEntity.ok(mejorJugadorDto);
			//return ResponseEntity.ok("id: " + mejorJugador.getIdJugador() + "\nNombre: " + mejorJugador.getNombre()
					//+ "\nRatio: " + df.format(mejorJugador.getRatio()) + " %");
		}
		return ResponseEntity.status(404).body("No hay jugadores en la lista.");

	}
	
	// OBTENER RANKING JUGADORES ORDENADO POR RATIO (MEJOR A PEOR)
	@GetMapping("/ranking")
	public ResponseEntity<?> getRankingOrdenado() {
		List<JugadorDto> jugadoresDto = iUtilities
				.getRankingOrdenadoByRatio(iJugadorService.getAllJugadores());
		
		return ResponseEntity.ok(jugadoresDto);
	}

}

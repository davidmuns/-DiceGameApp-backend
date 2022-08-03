package com.juegodados.dto;

import org.springframework.stereotype.Component;

@Component
public class JugadorDto {
	
	private int idJugador;
	private String nombre;
	private Double ratio;

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

}

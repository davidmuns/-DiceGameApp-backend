package com.juegodados.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "juego")
public class Juego implements Serializable {
    //
	private static final long serialVersionUID = -7967867338720290064L;

	@Id
	@Column(name = "id_juego")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJuego;
	
	@Column(name = "id_jugador")
	private int idJugador;

	@Column(name = "dado1")
	private int dado1;
	
	@Column(name = "dado2")
	private int dado2;

	@Column(name = "resultado")
	private int resultado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jugador", insertable = false, updatable = false)
	private Jugador jugador;

	public Juego() {

	}
	
	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getDado1() {
		return dado1;
	}


	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}


	public int getDado2() {
		return dado2;
	}


	public void setDado2(int dado2) {
		this.dado2 = dado2;
	}


	public int getResultado() {
		return resultado;
	}


	public void setResultado(int resultado) {
		this.resultado = resultado;
	}


	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

}

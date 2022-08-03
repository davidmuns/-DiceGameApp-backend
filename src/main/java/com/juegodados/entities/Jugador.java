package com.juegodados.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

	@Id
	@Column(name = "id_jugador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJugador;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "fecha", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date fecha;

	@Column(name = "ratio")
	private Double ratio = 0.0;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador", fetch = FetchType.LAZY)
	//private List<Juego> juegos;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador", fetch = FetchType.LAZY)
	//private List<Dado> dados;

	public Jugador() {
		fecha = new Date();
	}

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	/*
	 * public List<Juego> getJuegos() { return juegos; }
	 * 
	 * public void setJuegos(List<Juego> juegos) { this.juegos = juegos; }
	 * 
	 * public List<Dado> getDados() { return dados; }
	 * 
	 * public void setDados(List<Dado> dados) { this.dados = dados; }
	 */

}

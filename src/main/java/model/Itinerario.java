package model;

import java.util.List;

public class Itinerario {
	private String id_usuario;
	List<String> id_atracciones;

	public Itinerario(String id_usuario, List<String> id_atracciones) {
		this.id_usuario = id_usuario;
		this.id_atracciones = id_atracciones;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<String> getId_atracciones() {
		return id_atracciones;
	}

	public void setId_atracciones(List<String> id_atracciones) {
		this.id_atracciones = id_atracciones;
	}

	@Override
	public String toString() {
		return "Itinerario [id_usuario=" + id_usuario + ", id_atracciones=" + id_atracciones + "]";
	}

}

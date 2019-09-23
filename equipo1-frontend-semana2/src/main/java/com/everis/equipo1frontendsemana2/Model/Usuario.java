package com.everis.equipo1frontendsemana2.Model;

public class Usuario implements java.io.Serializable {

	private int idusuario;
	private String nombre;
	private String contrasena;

	public Usuario() {
	}

	public Usuario(int idusuario, String nombre, String contrasena) {
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}

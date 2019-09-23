package com.everis.equipo1frontendsemana2.Model;
// Generated 18/09/2019 12:21:43 PM by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;

public class Curso implements java.io.Serializable {

	private int id;
	private String nombrecurso;
	private String descripcion;
	private Set<Calendario> calendarios = new HashSet<Calendario>(0);

	public Curso() {
	}

	public Curso(int id, String nombrecurso) {
		this.id = id;
		this.nombrecurso = nombrecurso;
	}

	public Curso(int id, String nombrecurso, String descripcion, Set<Calendario> calendarios) {
		this.id = id;
		this.nombrecurso = nombrecurso;
		this.descripcion = descripcion;
		this.calendarios = calendarios;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrecurso() {
		return this.nombrecurso;
	}

	public void setNombrecurso(String nombrecurso) {
		this.nombrecurso = nombrecurso;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Calendario> getCalendarios() {
		return this.calendarios;
	}

	public void setCalendarios(Set<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

}

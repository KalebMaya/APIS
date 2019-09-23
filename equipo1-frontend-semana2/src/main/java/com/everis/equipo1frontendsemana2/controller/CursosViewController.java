package com.everis.equipo1frontendsemana2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.everis.equipo1frontendsemana2.Model.Curso;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean//lo vuelve el controlador asociado a la vista
public class CursosViewController {
	
	private String url ="http://localhost:9999/rest/curso/";
	private List<Curso> cursos = new ArrayList<Curso>();
	
	//para filtrar
	private List<Curso> cursosFiltrados = new ArrayList<Curso>();
	//con la libreria RestEasy permite hacer peticiones http (GET, POST, PUT, DELETE)
	ResteasyClient cliente = new ResteasyClientBuilder().build();
	
	private String nombre;//para los campos del fieldset
	private String descripcion;
	
	@PostConstruct//el equivalente del document.ready de JQuery
	public void init() throws Exception {
		consultar();
	}
	
	private void consultar() throws Exception {//metodo para actualizar tabla (no se accede desde la vista)
		ResteasyWebTarget target = cliente.target(UriBuilder.fromPath(url));
		Response response = target.request().get();
		String jsonRespuesta = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		setCursos(mapper.readValue(jsonRespuesta, new TypeReference<List<Curso>>() {}));
		cursosFiltrados = cursos;
	}

	public void guardar() throws Exception {//metodo para guardar cursos
		Curso cursonuevo = new Curso();
		cursonuevo.setNombrecurso(nombre);
		cursonuevo.setDescripcion(descripcion);
		ObjectMapper mapper = new ObjectMapper();
		String jsonCursoNuevo = mapper.writeValueAsString(cursonuevo);
		ResteasyWebTarget target = cliente.target(UriBuilder.fromPath(url));
		//este cliente es el de resteasy no el de nuestros modelos
		Response respuesta = target.request(MediaType.APPLICATION_JSON).
				post(Entity.json(jsonCursoNuevo));
		String jsonRespuesta = respuesta.readEntity(String.class);
		Curso cursoInsertado = mapper.readValue(jsonRespuesta, Curso.class);
		//de aca para abajo es enviar un mensaje de confirmacion
		String mensaje;
		if(cursoInsertado.getId() > 0) {
			mensaje = "Curso insertado con id " + cursoInsertado.getId();
			consultar();
		}else {
			mensaje = "Error al insertar, pruebe mas tarde";
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar curso", 
				mensaje);
		FacesContext.getCurrentInstance().addMessage(null, message);
		//hay que agregar un componente a la vista que reciba esto
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

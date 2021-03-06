package com.everis.equipouno.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.everis.equipouno.model.Alumno;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlumnoControllerTest extends BaseControllerTest {
	
	@Before
	public void setUp() {
		super.setUp();//llama a setup del padre
	}
	
	@Test
	public void testListar() throws Exception {
		String url = "/alumnos/";
		//Jackson para armar JSON
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Alumno> alumnos = mapper.readValue(jsonRespuesta, new TypeReference<List<Alumno>>() {});
		assertNotNull(alumnos);
		assertTrue(alumnos.size() > 0);
		for (Alumno alumno : alumnos) {
			assertTrue(alumno.getId()>0);
		}
	}
	
	@Test
	public void testInsertar() throws Exception {
		String url = "/alumnos/";
		Alumno alumno = new Alumno();
		alumno.setNombre("Marco");
		alumno.setAmaterno("Probador");
		alumno.setApaterno("Junior");
		//Jackson para armar JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(alumno);
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.post(url).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		Alumno recibido = mapper.readValue(jsonRespuesta, Alumno.class);
		assertTrue(recibido.getId() > 0);
	}

	@Test
	public void testActualizar() throws Exception {
		String url = "/alumnos/";
		Alumno alumno = new Alumno();
		alumno.setId(4);
		alumno.setNombre("Marco");
		alumno.setAmaterno("Actualizado");
		alumno.setApaterno("Junior");
		//Jackson para armar JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(alumno);
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.put(url).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		Alumno recibido = mapper.readValue(jsonRespuesta, Alumno.class);
		assertTrue(recibido.getId() == alumno.getId());
	}

	@Test
	public void testBorrar() throws Exception {
		int idalumno = 4;
		String url = "/alumnos/"+idalumno;
		//Jackson para armar JSON
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String respuesta = resultado.getResponse().getContentAsString();
		assertTrue(respuesta.equals("true") || respuesta.equals("false"));
	}

}

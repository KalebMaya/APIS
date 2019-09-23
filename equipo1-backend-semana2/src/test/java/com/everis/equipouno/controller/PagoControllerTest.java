package com.everis.equipouno.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.everis.equipouno.model.Inscripcion;
import com.everis.equipouno.model.Pago;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PagoControllerTest extends BaseControllerTest {

	@Before//se ejecuta antes de la prueba
	public void setUp() {
		super.setUp();//llama a setup del padre
	}
	
	@Test
	public void testListar() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertar() throws Exception {
		String url = "/pagos/";
		Pago pago = new Pago();
		pago.setFechaPago(new Date());
		pago.setMonto(400.25);
		//se crea la inscripcion
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setId(1);
		//se asocia al pago
		pago.setInscripcion(inscripcion);
		//Jackson para armar JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(pago);
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.post(url).
				contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		Pago recibido = mapper.readValue(jsonRespuesta, Pago.class);
		assertTrue(recibido.getId() > 0);
	}

	@Test
	public void testActualizar() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminar() {
		fail("Not yet implemented");
	}

}

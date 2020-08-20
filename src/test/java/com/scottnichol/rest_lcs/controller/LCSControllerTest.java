/**
 * Tests of LCSController.
 */
package com.scottnichol.rest_lcs.controller;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scottnichol.rest_lcs.model.LCSRequest;
import com.scottnichol.rest_lcs.service.LCSService;

@WebMvcTest
public class LCSControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LCSService lcsService;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void shouldReturnCastForExampleRequest() throws Exception {
		final List<String> strings = Arrays.asList("comcast", "comcastic", "broadcaster");
		when(lcsService.calculateLCS(anySet())).thenReturn("cast");
		this.mockMvc.perform(MockMvcRequestBuilders
			      .post("/lcs")
			      .content(asJsonString(new LCSRequest(strings)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.lcs.length()").value(1))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.lcs[0].value").value("cast"));
	}

	@Test
	public void shouldReturnBadRequestForNoBody() throws Exception {
		when(lcsService.calculateLCS(anySet())).thenReturn("cast");
		this.mockMvc.perform(MockMvcRequestBuilders
			      .post("/lcs")
			      .content("")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReturnBadRequestForWrongJson() throws Exception {
		when(lcsService.calculateLCS(anySet())).thenReturn("cast");
		this.mockMvc.perform(MockMvcRequestBuilders
			      .post("/lcs")
			      .content("{\"arrayOfInts\":[1, 2, 3]}")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}

	@Test
	public void shouldReturnBadRequestForImproperList() throws Exception {
		final List<String> strings = Arrays.asList("comcast", "comcastic", "broadcaster", "comcast");
		when(lcsService.calculateLCS(anySet())).thenReturn("cast");
		this.mockMvc.perform(MockMvcRequestBuilders
			      .post("/lcs")
			      .content(asJsonString(new LCSRequest(strings)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return objectMapper.writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}}

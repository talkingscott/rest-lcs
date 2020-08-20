package com.scottnichol.rest_ics.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.scottnichol.rest_lcs.service.LCSService;

public class LCSServiceTest {

	private LCSService lcsService = new LCSService();

	@Test
	void shouldReturnCaseForProvidedExample() {
		final List<String> strings = Arrays.asList("comcast", "comcastic", "broadcaster");
		final String lcs = lcsService.calculateLCS(new HashSet<String>(strings));
		assertEquals(lcs, "cast");
	}
}

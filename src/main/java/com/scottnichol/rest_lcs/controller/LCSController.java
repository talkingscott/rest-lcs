package com.scottnichol.rest_lcs.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scottnichol.rest_lcs.exception.ImproperSetException;
import com.scottnichol.rest_lcs.model.LCSRequest;
import com.scottnichol.rest_lcs.model.LCSResponse;
import com.scottnichol.rest_lcs.service.LCSService;

@RestController
public class LCSController {

	@Autowired
	private LCSService lcsService;

	/**
	 * Calculate the longest common substring.
	 * 
	 * @param request The request payload.
	 * @return The response with the longest common substring.
	 */
	@PostMapping("/lcs")
	public LCSResponse calculateLCS(@RequestBody LCSRequest request) {
		if (request == null) {
			throw new NullPointerException("request");
		}
		final List<String> values = request.getSetOfStrings().stream().map(v -> v.getValue()).collect(Collectors.toList());
		final Set<String> setOfValues = new HashSet<String>(values);
		if (values.size() != setOfValues.size()) {
			throw new ImproperSetException();
		}
		final String lcs = lcsService.calculateLCS(setOfValues);
		return new LCSResponse(lcs);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleException(HttpMessageNotReadableException e) {
	    return "The request must be well-formed JSON";
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleException(NullPointerException e) {
	    return "The request must be JSON of the proper format";
	}

	@ExceptionHandler(ImproperSetException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleException(ImproperSetException e) {
	    return "The strings in the request are not a proper set; there are duplicates.";
	}
}

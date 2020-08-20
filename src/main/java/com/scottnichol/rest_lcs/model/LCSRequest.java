package com.scottnichol.rest_lcs.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Request for longest common substring.
 */
public class LCSRequest {
	private List<LCSValue> setOfStrings;

	private LCSRequest() {
		
	}

	public LCSRequest(List<String> strings) {
		final List<LCSValue> values = strings.stream().map(s -> new LCSValue(s)).collect(Collectors.toList());
		this.setOfStrings = values;
	}

	public List<LCSValue> getSetOfStrings() {
		return Collections.unmodifiableList(this.setOfStrings);
	}
}

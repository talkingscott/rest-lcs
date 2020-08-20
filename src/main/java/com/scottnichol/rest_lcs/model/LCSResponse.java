package com.scottnichol.rest_lcs.model;

import java.util.Collections;
import java.util.List;

/**
 * Response for longest common substring.
 */
public class LCSResponse {

	private List<LCSValue> lcs;

	public LCSResponse(final String value) {
		this.lcs = Collections.singletonList(new LCSValue(value));
	}

	public List<LCSValue> getLcs() {
		return Collections.unmodifiableList(lcs);
	}
}

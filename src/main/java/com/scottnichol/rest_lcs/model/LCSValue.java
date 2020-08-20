package com.scottnichol.rest_lcs.model;

/**
 * Value within a longest common substring request.
 */
public class LCSValue {
	private String value;

	/**
	 * Default constructor for de-serialization.
	 */
	private LCSValue() {
	}

	public LCSValue(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}

package com.scottnichol.rest_lcs.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;

/**
 * Longest Common Substring (LCS) implementation.
 */
@Service
public class LCSService {
	/**
	 * Calculate the longest common substring for a set of strings.
	 * 
	 * cf. https://stackoverflow.com/questions/17150311/java-implementation-for-longest-common-substring-of-n-strings
	 * 
	 * @param strings The set of strings.
	 * @return The longest common substring.
	 */
	public String calculateLCS(Set<String> strings) {
	    LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
	    for (String s: strings) {
	        solver.add(s);
	    }
	    return solver.getLongestCommonSubstring().toString();
	}
}

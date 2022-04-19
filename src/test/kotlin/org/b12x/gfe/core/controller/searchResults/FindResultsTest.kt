package org.b12x.gfe.core.controller.searchResults

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FindResultsTest {

    @Test
    fun patternMatcher_identifiesMatches() {
        assertTrue(FindResults.patternMatcher("HLA-A".toRegex(), "HLA-A"))
        assertFalse(FindResults.patternMatcher("HLA-B".toRegex(), "HLA-A"))
        assertTrue(FindResults.patternMatcher("HLA-A".toRegex(), "HLA-A*01:01:01:01"))
        assertTrue(FindResults.patternMatcher("01".toRegex(), "HLA-A*01:01:01:01"))
        assertTrue(FindResults.patternMatcher("02".toRegex(), "HLA-A*01:01:01:02N"))
        assertTrue(FindResults.patternMatcher("\\*01".toRegex(), "HLA-A*01:01:01:01"))
        assertFalse(FindResults.patternMatcher("\\*02".toRegex(), "HLA-A*01:01:01:02N"))
    }

}
package org.b12x.gfe.plugins.namesearch.controller

import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData.generateSearchData
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BuildRegexStringTest {

    val nameSearchData1 = generateSearchData("01:02")
    val nameSearchData2 = generateSearchData("*01:02")

    @Test
    fun assembleRegexString_returnWorkingRegex() {
        val regex1 = BuildRegexString.assembleRegexString(nameSearchData1)
        assertTrue(FindResults.patternMatcher(regex1, "HLA-A*01:01:01:02N"))
        val regex2 = BuildRegexString.assembleRegexString(nameSearchData2)
        assertFalse(FindResults.patternMatcher(regex2, "HLA-A*01:01:01:02N"))
    }
}
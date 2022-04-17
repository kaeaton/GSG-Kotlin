package org.b12x.gfe.core.controller.searchResults

import org.b12x.gfe.core.controller.SearchData

object FindResults {
    fun findResults(searchData: SearchData) {

    }

    /**
     * Compares regex to a string.
     *
     * @params the regex, in regex form
     * @params the string to match
     * @return boolean
     */
    fun patternMatcher(regex: Regex, alleleName: String) = regex.containsMatchIn(alleleName)


}
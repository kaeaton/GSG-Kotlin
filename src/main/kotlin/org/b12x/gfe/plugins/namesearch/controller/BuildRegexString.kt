package org.b12x.gfe.plugins.namesearch.controller

object BuildRegexString {

    /**
     * Assembles a string for conversion to regex.
     *
     * @params a string of the search term
     * @return regex
     */
    fun assembleRegexString(searchTerm: String): Regex {

        // if no asterisk, add one to start
        if(searchTerm.get(0).isDigit()) {
            val searchTermFromStart = """\*$searchTerm"""
            return searchTermFromStart.toRegex()
        }

        // have to escape the asterisk for regex conversion
        val asteriskSearchTerm = searchTerm.replace("*", "\\*")

        return asteriskSearchTerm.toRegex()
    }
}
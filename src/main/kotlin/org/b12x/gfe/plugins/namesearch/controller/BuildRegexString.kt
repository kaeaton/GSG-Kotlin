package org.b12x.gfe.plugins.namesearch.controller

object BuildRegexString {

    /**
     * Assembles a string for conversion to regex.
     *
     * @params a GfeSearchData instance
     * @return a string ready to be converted to regex
     */
    fun assembleRegexString(nameSearchData: NameSearchData) {
        nameSearchData.regex = nameSearchData.searchTerm
    }
}
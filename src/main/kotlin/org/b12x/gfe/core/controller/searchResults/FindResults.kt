package org.b12x.gfe.core.controller.searchResults

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.b12x.gfe.core.controller.SearchData

object FindResults {

    /**
     * Uses a SearchData file to find requested results.
     *
     * @params a searchData derived object
     */

    fun findResults(searchData: SearchData) {
        csvReader().open(searchData.dataFile) {
            readAllAsSequence().forEach { row: List<String> ->
                if(patternMatcher(searchData.regex, row[1])) {
                    println(row)
                }
            }
        }
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
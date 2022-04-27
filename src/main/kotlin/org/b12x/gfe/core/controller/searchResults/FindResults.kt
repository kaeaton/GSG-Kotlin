package org.b12x.gfe.core.controller.searchResults

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.b12x.gfe.core.controller.displayText.DisplayText
import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.displayText.Result

object FindResults {

    /**
     * Uses a SearchData file to find requested results.
     *
     * @params a searchData derived object
     */
    fun findResultsNameSearch(searchData: SearchData): List<Result> {
        val displayText = DisplayText(searchData.tab)

        csvReader().open(searchData.dataFile) {
            readAllAsSequence().forEach { row: List<String> ->
                if(patternMatcher(searchData.regex, row[1])) {
                    searchData.results.add(Result(row[1], row[0]))
                    searchData.resultsCount++
                }
            }
        }

        return searchData.results.toList()
    }

    /**
     * Uses a SearchData file to find requested results.
     *
     * @params a searchData derived object
     */
    fun findResultsGfeSearch(searchData: SearchData): List<Result> {
        val displayText = DisplayText(searchData.tab)

        csvReader().open(searchData.dataFile) {
            readAllAsSequence().forEach { row: List<String> ->
                if(patternMatcher(searchData.regex, row[0])) {
                    searchData.results.add(Result(row[1], row[0]))
                    searchData.resultsCount++
                }
            }
        }

        return searchData.results.toList()
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
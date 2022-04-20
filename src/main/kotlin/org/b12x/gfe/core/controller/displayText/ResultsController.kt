package org.b12x.gfe.core.controller.displayText

import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.namesearch.view.NameSearchButtonSubmit
import tornadofx.*

class ResultsController() : Controller() {
    init {
        subscribe<ResultsRequest> {
            val results = processRequest(NameSearchButtonSubmit.searchData)
            fire(ResultsEvent(results))
        }
    }

    private fun processRequest(searchData: SearchData): List<Result> = FindResults.findResults(searchData)
}
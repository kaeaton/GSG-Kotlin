package org.b12x.gfe.core.controller.displayText

import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import org.b12x.gfe.plugins.namesearch.view.NameSearchTextFieldSearchTerm
import tornadofx.*

class ResultsController : Controller() {
    init {
        subscribe<ResultsRequest> {
//            runAsync {
                val searchTerm = NameSearchTextFieldSearchTerm.searchTerm
                val searchData = CreateNewNameSearchData.generateSearchData(searchTerm)
                val results = processRequest(searchData)
                println(results)
                println("we have reached the ResultsController")
                fire(ResultsEvent(results))
                println("In theory, we have fired a Results event")
//            }
        }
    }

    private fun processRequest(searchData: SearchData): List<Result> = FindResults.findResults(searchData)
}
package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import tornadofx.*

object NameSearchButtonSubmit : View("My View") {

    lateinit var searchData: SearchData

    override val root = vbox {
        button("Submit") {
            action {
                val searchTerm = NameSearchTextFieldSearchTerm.searchTerm
                searchData = CreateNewNameSearchData.generateSearchData(searchTerm)
                println("The name search submit button has been activated.")
                // fire(ResultsRequest)
                NameSearchTableView.data.clear()
                NameSearchTableView.data.addAll(FindResults.findResultsNameSearch(searchData))
            }
        }
    }
}

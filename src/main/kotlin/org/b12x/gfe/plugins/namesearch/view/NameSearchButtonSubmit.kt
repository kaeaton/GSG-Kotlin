package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.displayText.ResultsRequest
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import tornadofx.*

object NameSearchButtonSubmit : View("My View") {

    lateinit var searchData: SearchData

    override val root = vbox {
        button("Submit") {
            action {
                val searchTerm = NameSearchTextFieldSearchTerm.searchTerm
                searchData = CreateNewNameSearchData.generateSearchData(searchTerm.toString())
                fire(ResultsRequest())

            }
        }
    }
}

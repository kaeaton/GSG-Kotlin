package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.displayText.Result
import org.b12x.gfe.core.controller.displayText.ResultsRequest
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import tornadofx.*

object NameSearchButtonSubmit : View("My View") {

    lateinit var searchData: SearchData

    override val root = vbox {
        button("Submit") {
            action {
                val searchTerm = NameSearchTextFieldSearchTerm.searchTerm
                searchData = CreateNewNameSearchData.generateSearchData(searchTerm.toString())
                println("The name search submit button has been activated.")
                // fire(ResultsRequest)
                NameSearchTableView.data.clear()
                NameSearchTableView.data.addAll(FindResults.findResults(searchData)
//                    listOf(
//                        Result("HLA-B*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
//                        Result("HLA-C*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
//                        Result("HLA-D*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
//                    )
                )

            }
        }
    }
}

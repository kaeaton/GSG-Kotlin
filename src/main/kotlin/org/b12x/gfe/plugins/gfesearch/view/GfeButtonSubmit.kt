package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.CreateNewGfeSearchData
import tornadofx.*

object GfeButtonSubmit : View("Submit") {



    override val root = vbox {
        button("Submit") {
            action {
                val searchData = CreateNewGfeSearchData.generateSearchData()
                println("You pressed the GFE Search submit button.")
//                resultsTextField.text = "Data submitted"
                val results = FindResults.findResultsGfeSearch(searchData)
//                println(results)
                GfeTableViewData.gfeData.clear()
                GfeTableViewData.gfeData.addAll(results)
                GfeTextAreaInfo.infoTextArea.appendText("Search terms: ${searchData.header}\n")
                GfeTextAreaInfo.infoTextArea.appendText("Total results: ${searchData.resultsCount}\n")
//                GfeViewData.resetArraysSoft()
            }
        }
    }

    fun submitGfeSearchData() {
        val searchData = CreateNewGfeSearchData.generateSearchData()
        println("You pressed enter on a GFE Search textfield.")
        val results = FindResults.findResultsGfeSearch(searchData)

        GfeTableViewData.gfeData.clear()
        GfeTableViewData.gfeData.addAll(results)
        GfeTextAreaInfo.infoTextArea.appendText("Search terms: ${searchData.header}\n")
        GfeTextAreaInfo.infoTextArea.appendText("Total results: ${searchData.resultsCount}\n")
//        GfeViewData.resetArraysSoft()
    }
}


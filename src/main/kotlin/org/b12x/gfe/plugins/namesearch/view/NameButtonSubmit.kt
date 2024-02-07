package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

object NameButtonSubmit : View("My View") {

//    lateinit var searchData: SearchData

    override val root = vbox {
        button("Submit") {
            action {
//                val searchTerm = NameTextFieldSearch.searchTerm
//                val nameSearchInformationTextArea = tornadofx.find(NameTextAreaInfo::class)
//                searchData = CreateNewNameSearchData.generateSearchData(searchTerm)
//                println("The name search submit button has been activated.")
//                // fire(ResultsRequest)
//                NameTableViewData.data.clear()
//                NameTableViewData.data.addAll(FindResults.findResultsNameSearch(searchData))
//                nameSearchInformationTextArea.infoTextArea.appendText("Total results: ${searchData.resultsCount}\n")

            }
        }
    }
}

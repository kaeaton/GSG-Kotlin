package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.gfesearch.controller.CreateNewGfeSearchData
import tornadofx.*

object GfeSearchButtonSubmit : View("Submit") {

    override val root = vbox {
        button("Submit") {
            action {
                val searchData = CreateNewGfeSearchData.generateSearchData()
                println("You pressed the GFE Search submit button.")
//                resultsTextField.text = "Data submitted"
                val results = FindResults.findResultsGfeSearch(searchData)
                println(results)
                GfeSearchTableView.gfeData.clear()
                GfeSearchTableView.gfeData.addAll(results)
                val gfeSearchInformationTextArea = tornadofx.find(GfeSearchInformationTextArea::class)
                gfeSearchInformationTextArea.infoTextArea.appendText("Total results: ${searchData.resultsCount}\n")
                GfeSearchLayoutData.resetArraysSoft()
            }
        }
    }
}

//action {
//    GfeLayoutData.resetArraysSoft()
//    runAsync {
//        myController.loadText()
//        setOnAction {
//            GfeLayoutData.resetArraysSoft()
//            println("You pressed the GFE Search submit button.")
//        }
//     } ui {
//          resultsTextField.text = "Data submitted"
//     }
//}
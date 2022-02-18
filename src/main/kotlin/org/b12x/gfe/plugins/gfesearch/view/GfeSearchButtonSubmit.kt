package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.plugins.gfesearch.controller.CreateNewGfeSearchData
import tornadofx.*

class GfeSearchButtonSubmit : View("Submit") {

    override val root = vbox {
        button("Submit") {
            action {
                val searchData = CreateNewGfeSearchData.generateSearchData()
                GfeSearchLayoutData.resetArraysSoft()
                println("You pressed the GFE Search submit button.")
//                resultsTextField.text = "Data submitted"
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
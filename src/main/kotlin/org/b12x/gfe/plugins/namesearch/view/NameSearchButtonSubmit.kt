package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import tornadofx.*

object NameSearchButtonSubmit : View("My View") {
    override val root = vbox {
        button("Submit") {
            action {
                println("You pressed the Name Search submit button.")
                val searchTerm = find(NameSearchTextFieldSearchTerm::class).searchTermTextField.text
                val searchData = CreateNewNameSearchData.generateSearchData(searchTerm)

            }
        }
    }
}

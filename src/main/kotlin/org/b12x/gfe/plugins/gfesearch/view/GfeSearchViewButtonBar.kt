package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeSearchViewButtonBar : View("Buttons") {

    val resultsTextField = textfield("")

    override val root = vbox {
        button("Submit") {
            action {
                GfeSearchLayoutData.resetArraysSoft()
                println("You pressed the GFE Search submit button.")
                resultsTextField.text = "Data submitted"
            }
        }
        add(resultsTextField)
    }
}
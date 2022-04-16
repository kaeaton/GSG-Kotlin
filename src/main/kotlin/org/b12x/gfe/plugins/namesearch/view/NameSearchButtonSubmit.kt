package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

object NameSearchButtonSubmit : View("My View") {
    override val root = vbox {
        button("Submit") {
            action {
                println("You pressed the Name Search submit button.")
            }
        }
    }
}

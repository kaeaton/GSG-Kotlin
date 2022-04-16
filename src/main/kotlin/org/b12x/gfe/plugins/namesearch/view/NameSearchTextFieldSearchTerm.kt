package org.b12x.gfe.plugins.namesearch.view

import javafx.scene.control.TextField
import tornadofx.*

class NameSearchTextFieldSearchTerm : View("My View") {

    val searchTermTextField = textfield {
        style {
            prefWidth = Dimension(650.0, Dimension.LinearUnits.em)
        }
    }

    override val root = hbox {
        add(searchTermTextField)
    }
}

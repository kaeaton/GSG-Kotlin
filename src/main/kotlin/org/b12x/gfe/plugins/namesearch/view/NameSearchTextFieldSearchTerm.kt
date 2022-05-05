package org.b12x.gfe.plugins.namesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextField
import tornadofx.*

object NameSearchTextFieldSearchTerm : View("My View") {

    /* textfield contents */

    val searchTermTextField = textfield {
        textProperty().addListener { obs, old, new ->
            searchTerm = new
        }
        style {
            prefWidth = Dimension(650.0, Dimension.LinearUnits.em)
        }
    }

    val searchTermProperty = SimpleStringProperty(searchTermTextField.text)
    var searchTerm: String by searchTermProperty

    override val root = hbox {
        add(searchTermTextField)
    }
}

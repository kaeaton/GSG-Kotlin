package org.b12x.gfe.core.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Control
import tornadofx.Controller

interface ChoiceBoxLocus {

    var locusList : ObservableList<String>
    var currentLocus: String
    var choiceBoxLocus : ChoiceBox<String>
}
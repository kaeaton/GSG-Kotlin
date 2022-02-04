package org.b12x.gfe.core.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox

interface ChoiceBoxLocus {

    var locusObservableList : ObservableList<String>
    var currentLocus: String
    var choiceBoxLocus : ChoiceBox<String>
}
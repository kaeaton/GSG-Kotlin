package org.b12x.gfe.core.view

import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox

interface MenuLocus {

    var locusList : ObservableList<String>
    var currentLocus: String
    var menuLocus : ChoiceBox<String>
}
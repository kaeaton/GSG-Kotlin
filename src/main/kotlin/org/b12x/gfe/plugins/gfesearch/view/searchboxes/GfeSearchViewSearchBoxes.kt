package org.b12x.gfe.plugins.gfesearch.view.searchboxes

import javafx.scene.Parent
import javafx.scene.control.CheckBox
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*

interface GfeSearchViewSearchBoxes {
    val root: VBox
    fun completedSearchBoxGenerator(numberOfBoxes: Int): HBox
}
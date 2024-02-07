package org.b12x.gfe.plugins.gfesearch.view.searchboxes

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

interface GfeSearchBoxes {
    val root: VBox
    fun completedSearchBoxGenerator(numberOfBoxes: Int): HBox
}
package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import tornadofx.*

class GfeSearchLayoutData {

    /* Output Files*/
    var textFormat = "CVS" // Add to state
    var writeToFile = false // Add to state

    /* Search Array Data */
    val checkListProperty = SimpleListProperty<CheckBox>()
    var checkList by checkListProperty

    val textListProperty = SimpleListProperty<TextField>()
    var textList by textListProperty

    val searchBoxesProperty = SimpleObjectProperty<HBox>()
    var searchBoxes by searchBoxesProperty


//    fun resetArraysHard() {
//        checkList = mutableListOf<CheckBox>().asObservable()
//        textList = mutableListOf<TextField>().asObservable()
//    }
//
//    fun resetArraysSoft() {
//        checkList.forEach { it.isSelected = false }
//        textList.forEach { it.textProperty().value = "" }
//        textList[0].textProperty().value = "w"
//    }
}

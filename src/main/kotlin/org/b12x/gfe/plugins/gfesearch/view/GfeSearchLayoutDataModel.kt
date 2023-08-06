package org.b12x.gfe.plugins.gfesearch.view

import javafx.scene.Parent
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import tornadofx.*

class GfeSearchLayoutDataModel : ItemViewModel<GfeSearchLayoutData>() {
    val checkList = bind(GfeSearchLayoutData::checkListProperty)
    val textList = bind(GfeSearchLayoutData::textListProperty)
    val searchBoxes = bind(GfeSearchLayoutData::searchBoxesProperty)



    fun resetArraysSoft() {
        checkList.value.forEach { it.isSelected = false }
        textList.value.forEach { it.textProperty().value = "" }
        textList.value[0].textProperty().value = "w"
    }

    fun resetArraysHard() {
        checkList.value = observableListOf<CheckBox>()
        textList.value = observableListOf<TextField>()
    }
}
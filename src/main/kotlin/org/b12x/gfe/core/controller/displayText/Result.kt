package org.b12x.gfe.core.controller.displayText

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Result (alleleName: String, gfe: String){
    val gfeProperty = SimpleStringProperty(gfe)
    var gfeName by gfeProperty

    val alleleNameProperty = SimpleStringProperty(alleleName)
//    var alleleName by alleleNameProperty
}
package org.b12x.gfe.plugins.namesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.control.TextArea
import tornadofx.*

object NameSearchInformationTextArea : View("My View") {
    
    val infoTextArea = textarea {
        style {
            fontSize = Dimension(1.1, Dimension.LinearUnits.em)
            prefWidth = Dimension(425.0, Dimension.LinearUnits.px)
            prefHeight = Dimension(350.0, Dimension.LinearUnits.px)
        }
    }

    override val root = vbox {
        add(infoTextArea)

        style {
            alignment = Pos.CENTER
            hAlignment = HPos.CENTER
        }
    }
}

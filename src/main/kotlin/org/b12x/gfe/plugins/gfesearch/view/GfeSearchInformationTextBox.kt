package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import tornadofx.*

object GfeSearchInformationTextBox : View("My View") {

    override val root = vbox {
        val infoTextArea = textarea {
            style {
                prefWidth = Dimension(425.0, Dimension.LinearUnits.px)
                prefHeight = Dimension(300.0, Dimension.LinearUnits.px)
            }
        }
        style {
            alignment = Pos.CENTER
            hAlignment = HPos.CENTER
        }

    }
}

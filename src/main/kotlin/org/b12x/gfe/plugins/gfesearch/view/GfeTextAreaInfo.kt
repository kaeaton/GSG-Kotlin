package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import tornadofx.*

class GfeTextAreaInfo : View("My View") {

    val infoTextArea = textarea {
        style {
            fontSize = Dimension(1.1, Dimension.LinearUnits.em)
            prefWidth = Dimension(400.0, Dimension.LinearUnits.px)
            prefHeight = Dimension(330.0, Dimension.LinearUnits.px)
            padding = box(0.px)
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

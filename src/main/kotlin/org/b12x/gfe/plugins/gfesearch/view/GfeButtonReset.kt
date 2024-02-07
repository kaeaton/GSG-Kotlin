package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.Pos
import javafx.scene.text.TextAlignment
import tornadofx.*

object GfeButtonReset : View("Reset") {

    val resetButton = button("Reset\nSearch") {
        action {
            GfeViewMethods.resetArraysSoft()
        }
        style {
            textAlignment = TextAlignment.CENTER
            alignment = Pos.CENTER
        }
    }

    override val root = hbox {
        add(resetButton)
    }
}
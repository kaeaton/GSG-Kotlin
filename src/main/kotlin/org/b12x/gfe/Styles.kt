package org.b12x.gfe

import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val textAreas by cssclass()
    }

    init {
        root {
            prefHeight = 600.px
            prefWidth = 800.px
        }

        textAreas {
            padding = box(20.px)
            prefWidth = 760.px
            prefHeight = 300.px
        }

        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}
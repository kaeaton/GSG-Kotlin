package org.b12x.gfe.plugins.gfesearch.view

import javafx.scene.control.ToggleGroup
import tornadofx.*

class GfeRadioFileType : View() {

    private val toggleGroup = ToggleGroup()

    val printOptionCsv = radiobutton("CSV", toggleGroup)
    val printOptionTsv = radiobutton("TSV", toggleGroup)

    override val root = hbox {
        add(printOptionCsv)
        add(printOptionTsv)
        printOptionCsv.isSelected = true
        style {
            printOptionCsv.padding = insets(10,0)
        }
    }
}
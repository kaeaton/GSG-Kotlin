package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

class NameCheckboxPrint : View() {
    val printCheckBox = checkbox("Print to file") {
        style { padding = box(0.px, 0.px, 0.px, 0.px) }
    }

    override val root = hbox {
        add(printCheckBox)
    }
}
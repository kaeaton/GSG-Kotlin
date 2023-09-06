package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeContainerPrintOptions : View() {
    override val root = hbox {
        add(find(GfeCheckboxPrint::class).root)
        add(find(GfeRadioFileType::class).root)

        style {
            padding = box(0.px, 0.px, 0.px, 0.px)
        }
    }
}
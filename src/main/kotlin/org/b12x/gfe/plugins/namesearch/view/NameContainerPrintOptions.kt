package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

class NameContainerPrintOptions : View() {
    override val root = hbox {
        add(find(NameCheckboxPrint::class).root)
        add(find(NameRadioFileType::class).root)

        style {
            padding = box(0.px, 0.px, 0.px, 0.px)
        }
    }
}
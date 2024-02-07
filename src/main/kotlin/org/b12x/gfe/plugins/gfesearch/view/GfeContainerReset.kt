package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeContainerReset : View() {
    override val root = vbox {
//        add(gfeMenuLoci.root)
        add(find(GfeContainerMenu::class).root)
        add(GfeButtonReset.resetButton)
    }
}
package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeContainerMenu : View() {

    private val gfeMenuLocus = find(GfeMenuLocus::class)

    private var gfeMenuVersion = find(GfeMenuVersion::class)

    private val gfeMenuLoci = find(GfeMenuLoci::class)

    override val root = hbox {
//        add(gfeMenuLoci.root)
        add(gfeMenuVersion.menuVersion)
        add(gfeMenuLocus.menuLocus)
        style {
            padding = box(10.px, 0.px, 10.px, 10.px)
        }
    }
}

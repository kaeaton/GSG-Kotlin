package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeContainerMenu : View() {

    private val gfeMenuLocus = find(GfeMenuLocus::class)
//    private var locusChoiceBox = GfeMenuLocus.root

    private var gfeMenuVersion = find(GfeMenuVersion::class)
//    private var versionChoiceBox = gfeMenuVersion.root

    private val gfeMenuLoci = find(GfeMenuLoci::class)
//    private val lociChoiceBox = gfeMenuLoci.root

    override val root = hbox {
        add(gfeMenuLoci.root)
        add(gfeMenuVersion.menuVersion)
        add(gfeMenuLocus.menuLocus)
    }
}

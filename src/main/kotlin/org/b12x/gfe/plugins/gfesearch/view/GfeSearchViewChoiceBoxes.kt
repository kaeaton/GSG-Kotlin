package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeSearchViewChoiceBoxes : View("GFE Search Options") {

    private val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
    private var locusChoiceBox = gfeSearchChoiceBoxLocus.root

    private var gfeSearchChoiceBoxVersion = find(GfeSearchChoiceBoxVersion::class)
    private var versionChoiceBox = gfeSearchChoiceBoxVersion.root

    private val gfeSearchChoiceBoxLoci = GfeSearchChoiceBoxLoci
    private val lociChoiceBox = gfeSearchChoiceBoxLoci.root

    override val root = borderpane {
        left = hbox { add(GfeSearchChoiceBoxLoci.root) }
        center = hbox { add(versionChoiceBox) }
        right = hbox { add(locusChoiceBox) }
    }
}

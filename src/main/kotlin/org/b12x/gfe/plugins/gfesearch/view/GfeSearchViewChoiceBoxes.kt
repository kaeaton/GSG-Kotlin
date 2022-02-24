package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeSearchViewChoiceBoxes : View() {

    private val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
    private var locusChoiceBox = gfeSearchChoiceBoxLocus.root

//    private var gfeSearchChoiceBoxVersion = find(GfeSearchChoiceBoxVersion::class)
//    private var versionChoiceBox = gfeSearchChoiceBoxVersion.root

    private val gfeSearchChoiceBoxLoci = GfeSearchChoiceBoxLoci
    private val lociChoiceBox = gfeSearchChoiceBoxLoci.root

    override val root = borderpane {
        left = hbox { add(GfeSearchChoiceBoxLoci.root) }
        center = hbox { add(GfeSearchChoiceBoxVersion.root) }
        right = hbox { add(locusChoiceBox) }
    }
}

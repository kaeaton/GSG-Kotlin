package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeSearchViewChoiceBoxes : View() {

    private val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
//    private var locusChoiceBox = GfeSearchChoiceBoxLocus.root

    private var gfeSearchChoiceBoxVersion = find(GfeSearchChoiceBoxVersion::class)
//    private var versionChoiceBox = gfeSearchChoiceBoxVersion.root

    private val gfeSearchChoiceBoxLoci = find(GfeSearchChoiceBoxLoci::class)
//    private val lociChoiceBox = gfeSearchChoiceBoxLoci.root

    override val root = hbox {
        add(gfeSearchChoiceBoxLoci.root)
        add(gfeSearchChoiceBoxVersion.choiceBoxVersion)
        add(gfeSearchChoiceBoxLocus.choiceBoxLocus)
    }
}

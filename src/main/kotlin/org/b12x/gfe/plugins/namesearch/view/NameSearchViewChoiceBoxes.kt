package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

class NameSearchViewChoiceBoxes : View("My View") {

    private val nameSearchChoiceBoxLocus = find(NameSearchChoiceBoxLocus::class)

    private var nameSearchChoiceBoxVersion = find(NameSearchChoiceBoxVersion::class)

    private val nameSearchChoiceBoxLoci = find(NameSearchChoiceBoxLoci::class)

    override val root = hbox {
        add(nameSearchChoiceBoxLoci.root)
        add(nameSearchChoiceBoxVersion.choiceBoxVersion)
        add(nameSearchChoiceBoxLocus.choiceBoxLocus)
    }
}

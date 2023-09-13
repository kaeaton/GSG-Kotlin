package org.b12x.gfe.plugins.namesearch.view

import tornadofx.*

class NameContainerMenu : View("My View") {

    private val nameSearchChoiceBoxLocus = find(NameMenuLocus::class)

    private var nameMenuVersion = find(NameMenuVersion::class)

    private val nameMenuLoci = find(NameMenuLoci::class)

    override val root = hbox {
        add(nameMenuLoci.root)
        add(nameMenuVersion.menuVersion)
        add(nameSearchChoiceBoxLocus.menuLocus)
    }
}

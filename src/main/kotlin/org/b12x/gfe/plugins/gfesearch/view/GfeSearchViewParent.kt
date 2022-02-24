package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeSearchViewParent : View("GFE SEARCH") {

    private val stateContext = LociStateContextGfeSearch

    private var gfeSearchViewChoiceBoxes = GfeSearchViewChoiceBoxes()

    var gfeSearchBoxes = stateContext.createNewSearchBoxes()

    private val gfeSearchViewBottomHalf = GfeSearchViewBottomHalf()

    override val root = borderpane {
        top = hbox {
            add(gfeSearchViewChoiceBoxes.root)
        }

        center = vbox {
            add(gfeSearchBoxes.root)
        }

        bottom = gfeSearchViewBottomHalf.root

        style {
            padding = box(25.px)
        }
    }
}
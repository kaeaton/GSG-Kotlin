package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxesContainer
import tornadofx.*

class GfeSearchViewParent : View("GFE Search") {

    val stateContext = LociStateContextGfeSearch

    var gfeSearchViewChoiceBoxes = GfeSearchViewChoiceBoxes()

    var gfeSearchBoxes = stateContext.createNewSearchBoxes()
    val gfeSearchBoxesContainer = GfeSearchBoxesContainer()
    private val gfeSearchViewBottomHalf = GfeSearchViewBottomHalf()

    override val root = borderpane {
        top = hbox {
            add(gfeSearchViewChoiceBoxes.root)
        }

        center = hbox {
            add(gfeSearchBoxesContainer.root)
        }

        bottom = gfeSearchViewBottomHalf.root

        style {
            padding = box(25.px)
        }
    }
}
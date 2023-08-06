package org.b12x.gfe.plugins.gfesearch.view.searchboxes

import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchViewParent
import tornadofx.*

class GfeSearchBoxesContainer : View("My View") {

    val stateContext = LociStateContextGfeSearch

    var gfeSearchBoxes = stateContext.createNewSearchBoxes()

    override val root = hbox {
//        var gfeSearchBoxes = stateContext.createNewSearchBoxes()
        add(gfeSearchBoxes.root)
    }
}

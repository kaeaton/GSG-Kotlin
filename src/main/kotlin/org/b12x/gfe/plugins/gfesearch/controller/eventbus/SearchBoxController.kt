package org.b12x.gfe.plugins.gfesearch.controller.eventbus

import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.*
import tornadofx.Controller

class SearchBoxController : Controller() {
    init {
        subscribe<SearchBoxRequest> {
            println("SearchBoxController received request to subscribe.")
            val searchBoxes = loadSearchBoxes()
            println(searchBoxes)
            println("Firing off the searchBoxes")
            fire(SearchBoxEvent(searchBoxes))
        }
    }

    val stateContext = LociStateContextGfeSearch
    fun loadSearchBoxes(): GfeSearchViewSearchBoxes = stateContext.createNewSearchBoxes()
}

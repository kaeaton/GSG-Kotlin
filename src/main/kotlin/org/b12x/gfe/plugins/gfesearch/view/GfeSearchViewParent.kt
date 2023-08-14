package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleObjectProperty
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxesContainer
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxes
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.SearchBoxEvent
import tornadofx.*

class GfeSearchViewParent : View("GFE Search") {

    private val stateContext = LociStateContextGfeSearch

    private var gfeSearchViewChoiceBoxes = GfeSearchViewChoiceBoxes()

    val gfeSBProperty = SimpleObjectProperty<GfeSearchViewSearchBoxes>()
    var gfeSearchBoxes by gfeSBProperty

//    var gfeSearchBoxes = stateContext.createNewSearchBoxes()
    val gfeSearchBoxesContainer = GfeSearchBoxesContainer()
    private val gfeSearchViewBottomHalf = GfeSearchViewBottomHalf()



    override val root = borderpane {

        top = hbox {
            add(gfeSearchViewChoiceBoxes.root)
        }

        center = hbox {
            add(gfeSearchBoxes.root)

//            add(gfeSearchBoxesContainer.root)
        }

        bottom = gfeSearchViewBottomHalf.root

        style {
            padding = box(25.px)
        }
    }

    init {
        subscribe<SearchBoxEvent> { event ->
            println("Replacing the searchBoxes by EventBus.")
            root.center.replaceWith(event.searchBoxes.root)
        }
    }
}
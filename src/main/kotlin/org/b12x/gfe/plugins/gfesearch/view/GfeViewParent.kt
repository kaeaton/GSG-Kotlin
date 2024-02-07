package org.b12x.gfe.plugins.gfesearch.view

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeViewParent : View("GFE Search") {

    private val stateContext = LociStateContextGfeSearch
    private var gfeContainerMenu = GfeContainerMenu()

    var gfeSearchBoxes = stateContext.createNewSearchBoxes()
    private val gfeContainerBottomHalf = GfeContainerBottomHalf()

    override val root = borderpane {
        top { add(gfeContainerMenu.root) }

        center = vbox { add(gfeSearchBoxes.root) }

        bottom { add(gfeContainerBottomHalf.root) }

        style {
            padding = box(10.px, 0.px, 0.px, 10.px)
        }
    }

    fun swapSearchBoxes() {
        println("What locus we think we're loading: ${stateContext.locus}")
        println("What locusEnum we think we're loading: ${stateContext.locusEnum.fullName}")
        gfeSearchBoxes.root.removeFromParent()
        gfeSearchBoxes = stateContext.createNewSearchBoxes()
        this.root.center.add(gfeSearchBoxes.root)
    }
}
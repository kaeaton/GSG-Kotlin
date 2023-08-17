package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeViewParent : View("GFE Search") {

    private val stateContext = LociStateContextGfeSearch

    private var gfeContainerMenu = GfeContainerMenu()

    var gfeSearchBoxes = stateContext.createNewSearchBoxes()
    private val gfeContainerBottomHalf = GfeContainerBottomHalf()

//    fun swapSearchBoxes() {
//        root.center.replaceWith(stateContext.createNewSearchBoxes().root)
//    }

    override val root = borderpane {
        top { add(gfeContainerMenu.root) }

        center = vbox { add(gfeSearchBoxes.root) }

        bottom { add(gfeContainerBottomHalf.root) }

        style {
            padding = box(25.px)
        }
    }

    //    center = vbox {
//        add(gfeSearchBoxes.root)
////            add( hbox {
////                add(button("Add") { setOnAction { println("You pressed button \"Add\"") } })
////                add(button("Multiply") { setOnAction { println("You pressed button \"Multiply\"") } })
////                add(button("Equals") { setOnAction { println("You pressed button \"Equals\"") } })
////            })
//    }
//
//    bottom = vbox {
//        add(gfeSearchSubmitButton.root)
//        add(button("Exit") {
//            setOnAction { exitProcess(0) }
//        })
//    }
//    style {
//        padding = box(25.px)
//    }
//}
//
    fun swapSearchBoxes() {
        println("What locus we think we're loading: ${stateContext.locus}")
        gfeSearchBoxes.root.removeFromParent()
        gfeSearchBoxes = stateContext.createNewSearchBoxes()
        this.root.center.add(gfeSearchBoxes.root)
        println("GfeSearchView.swapSearchBoxes triggered")
    }
}
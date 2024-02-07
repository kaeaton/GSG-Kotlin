package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*

class NameViewParent : View("Name Search") {

    private val stateContext = LociStateContextNameSearch

    private var nameContainerMenu = NameContainerMenu()
//    private val nameSearchTextFieldSearchTerm = NameTextFieldSearch()
//    private val nameSearchTableView = NameTableViewData()
//    private val nameSearchInformationTextArea = NameTextAreaInfo()
    private val nameContainerBottomHalf = NameContainerBottomHalf()

    override val root = borderpane {
        top = vbox {
            add(nameContainerMenu.root)
        }

        center = hbox {
//            add(nameSearchTableView.root)
//            add(nameSearchInformationTextArea.root)
//            textarea {
//                style {
////                    width = 800px
//                }
//            }
            add (NameTextFieldSearch.root)
        }

        bottom = nameContainerBottomHalf.root

        style {
            padding = box(25.px)
        }
    }
}

package org.b12x.gfe.plugins.namesearch.view

import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*

class NameSearchViewParent : View("Name Search") {

    private val stateContext = LociStateContextNameSearch

    private var nameSearchViewChoiceBoxes = NameSearchViewChoiceBoxes()
//    private val nameSearchTextFieldSearchTerm = NameSearchTextFieldSearchTerm()
//    private val nameSearchTableView = NameSearchTableView()
//    private val nameSearchInformationTextArea = NameSearchInformationTextArea()
    private val nameSearchViewBottomHalf = NameSearchViewBottomHalf()

    override val root = borderpane {
        top = vbox {
            add(nameSearchViewChoiceBoxes.root)
        }

        center = hbox {
//            add(nameSearchTableView.root)
//            add(nameSearchInformationTextArea.root)
//            textarea {
//                style {
////                    width = 800px
//                }
//            }
            add (NameSearchTextFieldSearchTerm.root)
        }

        bottom = nameSearchViewBottomHalf.root

        style {
            padding = box(25.px)
        }
    }
}

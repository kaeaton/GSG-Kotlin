package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.layout.Priority
import tornadofx.*
import kotlin.system.exitProcess

class GfeContainerBottomHalf : View() {

    override val root = borderpane {
        top = hbox {  }

        left = vbox {
            style {
                prefWidth = Dimension(0.0, Dimension.LinearUnits.px)
            }
        }

        center = vbox {
            add(GfeTableViewData.root)
        }

        right = vbox {
//            val gfeSearchInformationTextBox = find(GfeTextAreaInfo::class)
            add(GfeTextAreaInfo.root)
        }

        bottom = hbox {

            add(Group(GfeButtonSubmit.root))

            add(Group(button("Exit") {
                action {
//                    var stateContext = LociStateContextGfeSearch
//                    stateContext.loci = GfeMenuLoci.choiceBoxLoci.value

                    exitProcess(0)
                }
                style {
                    padding = box(5.px, 17.px) // button internal padding
                    startMargin = Dimension(10.0, Dimension.LinearUnits.px)
                }
            }))

            style {
                alignment = Pos.CENTER
                hAlignment = HPos.CENTER
                padding = box(10.px, 0.px, 5.px, 0.px) // padding around individual elements?
            }

            hboxConstraints {
                marginRight = 10.0
                hGrow = Priority.ALWAYS
            }
        }

    }
}
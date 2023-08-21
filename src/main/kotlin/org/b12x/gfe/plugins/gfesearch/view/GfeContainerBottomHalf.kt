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
                    startMargin = Dimension(10.0, Dimension.LinearUnits.px)
                }
            }))

            style {
                alignment = Pos.CENTER
                hAlignment = HPos.CENTER
                padding = box(25.px, 10.px, 0.px, 10.px)
            }

            hboxConstraints {
                marginRight = 20.0
                hGrow = Priority.ALWAYS
            }
        }

    }
}
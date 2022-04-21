package org.b12x.gfe.plugins.namesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.layout.Priority
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchButtonSubmit
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextArea
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchTableView
import tornadofx.*
import kotlin.system.exitProcess

class NameSearchViewBottomHalf : View("My View") {

//    private val nameSearchTableView = NameSearchTableView()

    override val root = borderpane {
        top = hbox {  }

        left = vbox {
            style {
                prefWidth = Dimension(0.0, Dimension.LinearUnits.px)
            }
        }

        center = vbox {
            add(NameSearchTableView.root)
        }

        right = vbox {
            val nameSearchInformationTextBox = find(NameSearchInformationTextArea::class)
            add(nameSearchInformationTextBox.root)
        }

        bottom = hbox {
            add(Group(NameSearchButtonSubmit.root))

            add(Group(button("Exit") {
                action {
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

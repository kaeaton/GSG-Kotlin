package org.b12x.gfe.plugins.namesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.layout.Priority
import org.b12x.gfe.plugins.gfesearch.view.GfeButtonSubmit
import org.b12x.gfe.plugins.gfesearch.view.GfeContainerPrintOptions
import org.b12x.gfe.plugins.gfesearch.view.GfeTableViewData
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import tornadofx.*
import kotlin.system.exitProcess

class NameContainerBottomHalf : View("My View") {

//    private val nameSearchTableView = NameTableViewData()

    override val root = vbox {
        hbox {
            add(find(NameContainerPrintOptions::class).root)
            style {
                alignment = Pos.BASELINE_RIGHT
                padding = box(0.px, 100.px, 10.px, 0.px)
            }
        }
        hbox {
            add(find(NameTableViewData::class).root)
            add(find(NameTextAreaInfo::class).root)
            style {
                alignment = Pos.CENTER
                hAlignment = HPos.CENTER
//                padding = box(0.px, 10.px, 5.px, 0.px) // padding around individual elements?
            }
        }

        hbox {
            add(Group(NameButtonSubmit.root))

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
                padding = box(10.px, 10.px, 10.px, 0.px) // padding around individual elements?
            }

            hboxConstraints {
                marginRight = 10.0
                hGrow = Priority.ALWAYS
            }
        }
    }
}

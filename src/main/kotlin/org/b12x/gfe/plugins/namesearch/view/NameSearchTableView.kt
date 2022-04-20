package org.b12x.gfe.plugins.namesearch.view

import io.ktor.util.reflect.*
import javafx.geometry.HPos
import javafx.geometry.Pos
import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.displayText.Result
import org.b12x.gfe.core.controller.displayText.ResultsEvent
import tornadofx.*

class NameSearchTableView : View("My View") {

    var items = listOf(
        // data class at bottom of this file
        Result("HLA-A*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
        Result("HLA-A*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
        Result("HLA-A*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
    ).asObservable()

    override val root = hbox {
        tableview<Result> {
            readonlyColumn("Allele Name", Result::alleleName)
            readonlyColumn("GFE", Result::gfe)

            subscribe<ResultsEvent> { event ->
                items.setAll(event.results)
            }

            style {
                fontSize = Dimension(1.2, Dimension.LinearUnits.em)
                prefWidth = Dimension(650.0, Dimension.LinearUnits.px)
                prefHeight = Dimension(350.0, Dimension.LinearUnits.px)
            }

        }
        style {
            alignment = Pos.CENTER
            hAlignment = HPos.CENTER
        }
    }
}

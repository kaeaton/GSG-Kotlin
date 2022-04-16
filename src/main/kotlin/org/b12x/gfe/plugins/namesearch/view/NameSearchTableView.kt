package org.b12x.gfe.plugins.namesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import tornadofx.*

class NameSearchTableView : View("My View") {

    val data = listOf(
        // data class at bottom of this file
        NameSearchResult("HLA-A*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
        NameSearchResult("HLA-A*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
        NameSearchResult("HLA-A*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
    ).asObservable()

    override val root = hbox {
        tableview(data) {
            readonlyColumn("Name", NameSearchResult::name)
            readonlyColumn("GFE", NameSearchResult::gfeName)

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

data class NameSearchResult(val name: String, val gfeName: String)

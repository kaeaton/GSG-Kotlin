package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import org.b12x.gfe.core.controller.displayText.Result
import tornadofx.*

object GfeTableViewData : View() {

    val gfeData = observableListOf(
        Result("HLA-A*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
        Result("HLA-A*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
        Result("HLA-A*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
    )

    var testname = gfeData[0].gfeName.split("-")

//    var prettyTableView = tableview(data) {
//        readonlyColumn("Name", GfeSearchResult::name)
//        for (i in 0 until data[0].gfeName.split(",").size) {
//            column<String, String>("test $i", { it.value})
//            populate {
//                ReadOnlyStringWrapper(it.value)
//
//            }
//        }
//    }

    val dataTable = tableview(gfeData) {
        column("Allele Name", Result::alleleNameProperty)
        column("GFE", Result::gfeProperty) //.remainingWidth()


        style {
            fontSize = Dimension(1.2, Dimension.LinearUnits.em)
            prefWidth = Dimension(600.0, Dimension.LinearUnits.px)
            prefHeight = Dimension(330.0, Dimension.LinearUnits.px)
            startMargin = 0.px
        }
    }

    override val root = hbox {
        add(dataTable)

        style {
            alignment = Pos.CENTER
            hAlignment = HPos.CENTER
        }
    }
}
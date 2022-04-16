package org.b12x.gfe.plugins.gfesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import tornadofx.*

object GfeSearchTableView : View() {

    val data = listOf(
        // data class at the bottom of this file
        GfeSearchResult("HLA-A*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
        GfeSearchResult("HLA-A*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
        GfeSearchResult("HLA-A*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
    ).asObservable()

    val tableData = mapOf(
        "Fruit" to arrayOf("apple", "pear", "Banana"),
        "Veggies" to arrayOf("beans", "cauliflower", "kale"),
        "Meat" to arrayOf("poultry", "pork", "beef")
    )

    var testname = data[0].gfeName.split("-")

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


        override val root = hbox {
        tableview(data) {
            readonlyColumn("Name", GfeSearchResult::name)
            readonlyColumn("GFE", GfeSearchResult::gfeName)

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




//    val example = treetableview<String>(TreeItem("Items")) {
//        column<String, String>("Type", { it.value.valueProperty() })
//        populate {
//            if (it.value == "Items") tableData.keys
//            else tableData[it.value]?.asList()
//        }
//    }

}

data class GfeSearchResult(val name: String, val gfeName: String)
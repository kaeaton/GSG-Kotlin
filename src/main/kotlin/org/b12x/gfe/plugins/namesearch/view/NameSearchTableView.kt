package org.b12x.gfe.plugins.namesearch.view

import javafx.geometry.HPos
import javafx.geometry.Pos
import org.b12x.gfe.core.controller.displayText.Result
import org.b12x.gfe.core.controller.displayText.ResultsEvent
import tornadofx.*

object NameSearchTableView : View("My View") {

    var data = observableListOf(
        Result("HLA-A*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
        Result("HLA-A*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
        Result("HLA-A*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
    )//.asObservable()



    val dataTable = tableview(data) {
        column("Allele Name", Result::alleleNameProperty)
        column("GFE", Result::gfeProperty)

        style {
            fontSize = Dimension(1.2, Dimension.LinearUnits.em)
            prefWidth = Dimension(650.0, Dimension.LinearUnits.px)
            prefHeight = Dimension(350.0, Dimension.LinearUnits.px)
        }

        runAsync {
            subscribe<ResultsEvent> { event ->
                print("I subscribed to resultsEvent, and all I got was this lousy tee-shirt")
                println(data.setAll(event.results))
            }
        }
    }

    override val root = hbox {
        add (dataTable)

        style {
            alignment = Pos.CENTER
            hAlignment = HPos.CENTER
        }
    }
}

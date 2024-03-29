package org.b12x.gfe.plugins.gfesearch.view

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.CreateNewGfeSearchData
import org.b12x.gfe.plugins.gfesearch.model.output.files.*
import tornadofx.*

object GfeViewMethods {

    /* Output Files*/
    var textFormat = "CVS" // Add to state
    var writeToFile = false // Add to state

    /* Search Array Data */
    var checkList: MutableList<CheckBox> = ArrayList()
    var textList: MutableList<TextField> = ArrayList()

    fun resetArraysHard() {
        checkList = ArrayList()
        textList = ArrayList()
    }

    fun resetArraysSoft() {
        checkList.forEach { it.isSelected = false }
        textList.forEach { it.textProperty().value = "" }
        textList[0].textProperty().value = "w"
    }

    fun submitData() {
        val searchData = CreateNewGfeSearchData.generateSearchData()
        println("You pressed enter on a GFE Search.")
        val gfeTextAreaInfo = find(GfeTextAreaInfo::class)
        val gfeTableViewData = find(GfeTableViewData::class)

        FindResults.findResultsGfeSearch(searchData)
        gfeTableViewData.gfeData.clear()
        gfeTableViewData.gfeData.addAll(searchData.results)
//        SmartResize.POLICY.requestResize(gfeTableViewData.dataTable)
        gfeTextAreaInfo.infoTextArea.appendText("Search terms: ${searchData.header}\n")
        gfeTextAreaInfo.infoTextArea.appendText("Total results: ${searchData.resultsCount}\n")

        if (searchData.writeToFile) {
            when (searchData.textFormat) {
                "csv" -> GfeOutputCsv(searchData).writeData(searchData)
                "tsv" -> GfeOutputTsv(searchData).writeData(searchData)
            }
        }
    }
}

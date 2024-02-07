package org.b12x.gfe.plugins.gfesearch.controller.searchdata

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.SearchData
import java.io.File
import org.b12x.gfe.core.controller.displayText.Result

data class GfeSearchData(
    override val loci: String = "HLA",
    override val version: String = "2.0.0",
    override val locus: String = "HLA-A",
    val checkBoxList: MutableList<CheckBox>,
    val textFieldList: MutableList<TextField>,
    override var regex: Regex = "".toRegex(),
    var header: String = "",
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false,
    override val dataFile: File,
    override val tab: String = "GFE",
    override val results: MutableList<Result> = mutableListOf(),
    override var resultsCount: Int = 0
) : SearchData
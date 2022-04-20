package org.b12x.gfe.plugins.namesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.SearchData
import org.b12x.gfe.core.controller.displayText.Result
import java.io.File

data class NameSearchData(
    override val loci: String = "HLA",
    override val version: String = "2.0.0",
    override val locus: String = "HLA-A",
    val searchTerm: String,
    override var regex: Regex = "".toRegex(),
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false,
    override val dataFile: File,
    override val tab: String = "NAME",
    override val results: MutableList<Result> = mutableListOf()
) : SearchData

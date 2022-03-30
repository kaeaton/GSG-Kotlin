package org.b12x.gfe.plugins.gfesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.SearchData
import java.io.File

data class GfeSearchData(
    override val loci: String = "HLA",
    override val version: String = "2.0.0",
    override val locus: String = "HLA-A",
    val checkBoxList: MutableList<CheckBox>,
    val textFieldList: MutableList<TextField>,
    var regex: String = "",
    var header: String = "",
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false,
    val dataFile: File
) : SearchData
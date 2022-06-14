package org.b12x.gfe.plugins.gfesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

data class GfeSearchData(
    val loci: String = "HLA",
    val version: String = "2.0.0",
    val locus: String = "HLA-A",
    val checkBoxList: List<CheckBox>,
    val textFieldList: List<TextField>,
    var regex: String = "",
    var header: String = "",
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false
)
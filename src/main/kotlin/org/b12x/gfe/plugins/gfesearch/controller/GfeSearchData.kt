package org.b12x.gfe.plugins.gfesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

data class GfeSearchData(
    val loci: String = "HLA",
    val version: String = "2.0.0",
    val locus: String = "HLA-A",
    val checkArray: ArrayList<CheckBox> = ArrayList(),
    val textArray: ArrayList<TextField> = ArrayList(),
    val regex: String = "",
    val header: String = "",
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false
)
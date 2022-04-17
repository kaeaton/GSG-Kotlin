package org.b12x.gfe.plugins.namesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.SearchData
import java.io.File

data class NameSearchData(
    override val loci: String = "HLA",
    override val version: String = "2.0.0",
    override val locus: String = "HLA-A",
    var searchTerm: String,
    override var regex: String = "",
    val textFormat: String = "CSV",
    val writeToFile: Boolean = false,
    val dataFile: File
) : SearchData

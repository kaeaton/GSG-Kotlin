package org.b12x.gfe.plugins.gfesearch.model.output.files

import org.b12x.gfe.core.controller.tabstate.TabStateContext
import org.b12x.gfe.core.model.output.outputFile
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import java.lang.StringBuilder

class GfeOutputTsv(rawdata: MutableList<Any>) : outputFile {
    private val stateContext = LociStateContextGfeSearch

    override val headerLine1 = StringBuilder("File generated at: ")
    override val headerLine2 = StringBuilder("Data source: ")
    override val headerLine3 = StringBuilder("Search parameters: ")
    override val headerLine4 = StringBuilder(whichLocus())
    override val rawData = rawdata
    override val fileSuffix = "tsv"

    fun whichLocus() = when (stateContext.loci) {
        "HLA" -> "IMGT/HLA Database Version: "
        "KIR" -> "IPD-KIR Database Version: "
        "TEST" -> "Test Database Version: "
        else -> {
            "IMGT/HLA Database Version: "
        }
    }
}
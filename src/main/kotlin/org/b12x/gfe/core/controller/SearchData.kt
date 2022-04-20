package org.b12x.gfe.core.controller

import java.io.File
import org.b12x.gfe.core.controller.displayText.Result

interface SearchData {
    val loci: String
    val version: String
    val locus: String
    val regex: Regex
    val dataFile: File
    val tab: String
    val results: MutableList<Result>
}
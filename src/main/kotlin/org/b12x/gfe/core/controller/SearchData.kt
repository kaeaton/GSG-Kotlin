package org.b12x.gfe.core.controller

import java.io.File

interface SearchData {
    val loci: String
    val version: String
    val locus: String
    val regex: Regex
    val dataFile: File
}
package org.b12x.gfe.core.model.output

interface outputFile {
    val headerLine1: StringBuilder
    val headerLine2: StringBuilder
    val headerLine3: StringBuilder
    val headerLine4: StringBuilder
    val rawData: MutableList<Any>
    val fileSuffix: String
}
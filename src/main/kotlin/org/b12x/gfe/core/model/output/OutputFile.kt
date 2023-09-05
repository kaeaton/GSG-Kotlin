package org.b12x.gfe.core.model.output

import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.StringBuilder

interface OutputFile {
    val headerLine1: String
    val headerLine2: String
    val headerLine3: String
    val headerLine4: String
    val headerLine5: String
    val fileSuffix: String
    val fileDestination: String
    val dateTime: String
    val fileName: String
    val destinationFile: String

    fun formatDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")
        val current = LocalDateTime.now().format(formatter)
        return current
    }

    fun buildHeaderLine1(): String {
        val stringInProcess = StringBuilder("File generated at: ")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        return stringInProcess.append(current).append("\n").toString()
    }

    fun buildHeaderLine2(loci: String, locus: String): String {
        val stringInProcess = StringBuilder("Data Source: ")
        stringInProcess.append(whichLocusLoc(loci))
        stringInProcess.append(" - ")
        return stringInProcess.append("$locus\n").toString()
    }
    fun buildHeaderLine3(searchTerm: String): String {
        val stringInProcess = StringBuilder("Search parameters: ")
        return stringInProcess.append("$searchTerm\n").toString()
    }
    fun buildHeaderLine4(loci: String, version: String): String {
        val stringInProcess = StringBuilder(whichLocusDB(loci))
        return stringInProcess.append("$version\n").toString()
    }

    fun buildHeaderLine5(numOfResults: Int): String {
        val stringInProcess = StringBuilder("Total Results: ")
        return stringInProcess.append("$numOfResults\n").toString()
    }

    private fun whichLocusLoc(loci: String) = when (loci) {
        "HLA" -> "https://dash13-gfedb.b12x.org"
        "KIR" -> "https://dash13-gfedb.b12x.org"
        "TEST" -> "Test Data Location Version"
        else -> {
            "https://dash13-gfedb.b12x.org"
        }
    }

    private fun whichLocusDB(loci: String) = when (loci) {
        "HLA" -> "IMGT/HLA Database Version: "
        "KIR" -> "IPD-KIR Database Version: "
        "TEST" -> "Test Database Version: "
        else -> {
            "IMGT/HLA Database Version: "
        }
    }
}
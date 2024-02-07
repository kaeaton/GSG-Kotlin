package org.b12x.gfe.core.model.output

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
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
    val headerLine6: String
    val fileSuffix: String
    val fileDestination: String
    val dateTime: String
    val fileName: String
    val destinationFile: String

    /**
     * @returns formatted current date time "yyyy-MM-dd HH:mm"
     */
    fun formatDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")
        val current = LocalDateTime.now().format(formatter)
        return current
    }

    /**
     * @returns "@ yyyy-MM-dd HH:mm"
     */
    fun buildHeaderLine1(): String {
        val stringInProcess = StringBuilder("@ ")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        return stringInProcess.append(current).append("\n").toString()
    }

    /**
     * @returns "@ source-database"
     */
    fun buildHeaderLine2(loci: String): String {
        return "@ ${whichLocusLoc(loci)}\n"
    }

    /**
     * @returns "@ locus Search-term-key"
     */
    fun buildHeaderLine3(locus: String): String {
        val stringInProcess = StringBuilder("@ $locus ")
        return stringInProcess.append("${searchStringKey(locus)}\n").toString()
    }

    /**
     * @returns "@ search-term"
     */
    fun buildHeaderLine4(searchTerm: String): String {
        return "@ $searchTerm\n"
    }

    /**
     * @returns "@ version"
     */
    fun buildHeaderLine5(version: String): String {
        return "@ $version\n"
    }

    /**
     * @returns "@ Total Results: #-of-results"
     */
    fun buildHeaderLine6(totalResults: Int): String {
        return "@ Total Results: $totalResults\n"
    }

    private fun whichLocusLoc(loci: String) = when (loci) {
        "HLA" -> "https://dash13-gfedb.b12x.org"
        "KIR" -> "https://dash13-gfedb.b12x.org"
        "TEST" -> "Test Data Location Version"
        else -> {
            "https://dash13-gfedb.b12x.org"
        }
    }

    private fun searchStringKey(locus: String): String {
        val exons = (HlaLoci.values().find { it.fullName == locus } as LociEnum).exons
        val key = StringBuilder("U5-")
        for (i in 1 until exons) {
            key.append("E$i-")
            key.append("I$i-")
        }
        key.append("E$exons-U3")
        return key.toString()
    }
}
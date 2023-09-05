package org.b12x.gfe.plugins.gfesearch.model.output.files

import org.b12x.gfe.core.model.output.OutputFile
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.GfeSearchData
import org.b12x.gfe.utilities.FileManagement
import java.io.File

class GfeOutputPretty(searchData: GfeSearchData) : OutputFile {

    override val headerLine1 = super.buildHeaderLine1()
    override val headerLine2 = super.buildHeaderLine2(searchData.loci, searchData.locus)
    override val headerLine3 = super.buildHeaderLine3(searchData.header)
    override val headerLine4 = super.buildHeaderLine4(searchData.loci, searchData.version)
    override val headerLine5 = super.buildHeaderLine5(searchData.resultsCount)
    override val fileSuffix = "txt"
    override val fileDestination = "${System.getProperty("user.home")}/GSG"
    override val dateTime = super.formatDateTime()
    override val fileName = "${searchData.version}_${searchData.locus}_${dateTime}"

    override val destinationFile = FileManagement.createFile(
        whereTheFileShouldGo = fileDestination,
        fileName = fileName,
        fileSuffix = fileSuffix
    )

    fun writeData(searchData: GfeSearchData) {
        File(destinationFile).bufferedWriter().use {
                out ->

            // Header
            out.write(headerLine1)
            out.newLine()
            out.write(headerLine2)
            out.newLine()
            out.write(headerLine3)
            out.newLine()
            out.write(headerLine4)
            out.newLine()
            out.write(headerLine5)
            out.newLine()

            // Results
            searchData.results.forEach {
                out.write(it.toString())
            }
        }
    }
}
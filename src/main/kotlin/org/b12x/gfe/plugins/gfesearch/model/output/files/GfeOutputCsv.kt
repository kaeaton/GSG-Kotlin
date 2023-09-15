package org.b12x.gfe.plugins.gfesearch.model.output.files

import org.b12x.gfe.core.model.output.OutputFile
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.GfeSearchData
import org.b12x.gfe.utilities.FileManagement
import java.io.File

class GfeOutputCsv(searchData: GfeSearchData) : OutputFile {

    override val headerLine1 = super.buildHeaderLine1()
    override val headerLine2 = super.buildHeaderLine2(searchData.loci)
    override val headerLine3 = super.buildHeaderLine3(searchData.locus)
    override val headerLine4 = super.buildHeaderLine4(searchData.header)
    override val headerLine5 = super.buildHeaderLine5(searchData.version)
    override val headerLine6 = super.buildHeaderLine6(searchData.resultsCount)
    override val fileSuffix = "csv"
    override val fileDestination = "${System.getProperty("user.home")}/Documents/GSG"
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
            out.append(headerLine2)
            out.append(headerLine3)
            out.append(headerLine4)
            out.append(headerLine5)
            out.append(headerLine6)

            // Results
            searchData.results.forEach {
                var line = ("${it.alleleNameProperty.value},${it.gfeProperty.value}\n").toString()
                out.append(line)
            }

            // close
            out.close()
        }
    }

}
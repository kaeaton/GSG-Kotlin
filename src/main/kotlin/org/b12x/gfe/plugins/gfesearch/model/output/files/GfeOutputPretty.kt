package org.b12x.gfe.plugins.gfesearch.model.output.files

import org.b12x.gfe.core.model.output.outputFile
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.GfeSearchData

class GfeOutputPretty(searchData: GfeSearchData) : outputFile {

    override val headerLine1 = super.buildHeaderLine1()
    override val headerLine2 = super.buildHeaderLine2(searchData.loci, searchData.locus)
    override val headerLine3 = super.buildHeaderLine3(searchData.header)
    override val headerLine4 = super.buildHeaderLine4(searchData.loci, searchData.version)
    override val headerLine5 = super.buildHeaderLine5(searchData.resultsCount)
    override val fileSuffix = "txt"
}
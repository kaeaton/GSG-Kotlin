package org.b12x.gfe.plugins.gfesearch.controller.searchdata

import org.b12x.gfe.core.model.GetDataFiles
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildHeaderString
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildRegexString
import org.b12x.gfe.plugins.gfesearch.view.GfeViewMethods
import java.io.File

object CreateNewGfeSearchData {

    private val stateContext = LociStateContextGfeSearch

    fun generateSearchData(): GfeSearchData {
        val gfeSearchData = GfeSearchData(
            loci = stateContext.loci,
            version = stateContext.version,
            locus = stateContext.locus,
            checkBoxList = GfeViewMethods.checkList,
            textFieldList = GfeViewMethods.textList,
            regex = "".toRegex(),
            header = "",
            textFormat = GfeViewMethods.textFormat,
            writeToFile = GfeViewMethods.writeToFile,
            dataFile = File(
                GetDataFiles.retrieveDataFiles(
                    stateContext.loci,
                    stateContext.version,
                    stateContext.locus
                )
            )
        )

        BuildRegexString.assembleRegexString(gfeSearchData)
        BuildHeaderString.assembleHeaderString(gfeSearchData)

        return gfeSearchData
    }
}
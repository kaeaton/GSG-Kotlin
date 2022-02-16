package org.b12x.gfe.plugins.gfesearch.controller

import org.b12x.gfe.plugins.gfesearch.controller.regex.BuildHeaderString
import org.b12x.gfe.plugins.gfesearch.controller.regex.BuildRegexString
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData

object CreateNewGfeSearchData {
    fun generateSearchData(): GfeSearchData {
        val gfeSearchData = GfeSearchData(
            loci = GfeSearchLayoutData.currentLoci,
            version = GfeSearchLayoutData.currentVersion,
            locus = GfeSearchLayoutData.currentLocus,
            checkBoxList = GfeSearchLayoutData.checkList,
            textFieldList = GfeSearchLayoutData.textList,
            regex = "",
            header = "",
            textFormat = GfeSearchLayoutData.textFormat,
            writeToFile = GfeSearchLayoutData.writeToFile
        )

        BuildRegexString.assembleRegexString(gfeSearchData)
        BuildHeaderString.assembleHeaderString(gfeSearchData)

        return gfeSearchData
    }
}
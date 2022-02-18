package org.b12x.gfe.plugins.gfesearch.controller

import org.b12x.gfe.core.model.DataFiles
import org.b12x.gfe.plugins.gfesearch.controller.regex.BuildHeaderString
import org.b12x.gfe.plugins.gfesearch.controller.regex.BuildRegexString
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import java.io.File

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
            writeToFile = GfeSearchLayoutData.writeToFile,
            dataFile = File(
                DataFiles.retrieveDataFiles(
                    GfeSearchLayoutData.currentLoci,
                    GfeSearchLayoutData.currentVersion,
                    GfeSearchLayoutData.currentLocus
                )
            )
        )

        BuildRegexString.assembleRegexString(gfeSearchData)
        BuildHeaderString.assembleHeaderString(gfeSearchData)

        return gfeSearchData
    }


}
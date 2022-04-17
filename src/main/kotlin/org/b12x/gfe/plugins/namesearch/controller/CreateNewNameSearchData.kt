package org.b12x.gfe.plugins.namesearch.controller

import org.b12x.gfe.core.model.DataFiles
import org.b12x.gfe.plugins.gfesearch.controller.CreateNewGfeSearchData
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import java.io.File


object CreateNewNameSearchData {

    private val stateContext = LociStateContextNameSearch

    fun generateSearchData(searchTerm: String): NameSearchData {
        val nameSearchData = NameSearchData(
            loci = stateContext.loci,
            version = stateContext.version,
            locus = stateContext.locus,
            searchTerm = searchTerm,
            regex = "",
            textFormat = GfeSearchLayoutData.textFormat,
            writeToFile = GfeSearchLayoutData.writeToFile,
            dataFile = File(
                DataFiles.retrieveDataFiles(
                    stateContext.loci,
                    stateContext.version,
                    stateContext.locus
                )
            )
        )

        return nameSearchData
    }
}
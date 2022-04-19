package org.b12x.gfe

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.controller.searchResults.FindResults
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.plugins.namesearch.controller.CreateNewNameSearchData
import org.b12x.gfe.utilities.FileManagement
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.launch


fun main() {

    /* Prefs Reset */
//    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Program start */
    launch<GSG>()

//    val testData = CreateNewNameSearchData.generateSearchData("54")
//    FindResults.findResults(testData)
}




package org.b12x.gfe

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.*


fun main() {

    // necessary to initialize JavaFX
//    val jfxPanel = JFXPanel()

    /* Prefs Reset */
    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Event Bus Controller wakeup call */
//    find(ResultsController::class)
//
//    var data = listOf(
//        Result("HLA-C*01:01:01:01", "HLA-Aw2-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-4"),
//        Result("HLA-C*01:01:01:02N", "HLA-Aw3-1-1-1-2-1-1-1-1-1-1-1-1-1-1-1-3"),
//        Result("HLA-C*01:01:01:03", "HLA-Aw2-1-2-1-1-1-1-1-1-1-1-1-1-1-1-1-4")
//    )
//
//    fire(ResultsEvent(data))



    /* Program start */
    launch<GSG>()

    /* Internet Access */
    InternetAccess()

//    val testData = CreateNewNameSearchData.generateSearchData("")
//    FindResults.findResults(testData)
}




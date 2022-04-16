package org.b12x.gfe

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import tornadofx.launch


fun main() {

    /* Internet Access */
//    InternetAccess.isInternetAvailable()

    /* Prefs Reset */
    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Program start */
    launch<GSG>()
}




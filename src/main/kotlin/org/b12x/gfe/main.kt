package org.b12x.gfe

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.*


fun main() {

    /* Prefs Reset */
//    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Program start */
    launch<GSG>()

    /* Internet Access */
    InternetAccess()
}

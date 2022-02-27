package org.b12x.gfe

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextBox
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.find
import tornadofx.launch
import tornadofx.text
import java.io.File


fun main() {

    /* Internet Access */
//    InternetAccess.isInternetAvailable()

    /* Prefs Reset */
//    PrefsCore.nuclearOption()
//    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Program start */
    launch<GSG>()
}




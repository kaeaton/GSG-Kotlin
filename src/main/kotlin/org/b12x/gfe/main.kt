package org.b12x.gfe

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.model.datadownload.version.DownloadVersion
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.*


fun main() {

    /* Prefs Reset */
    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Internet Access */
    val internetAccess = InternetAccess()

    runBlocking {
        internetAndVersions(internetAccess)
    }

    /* Program start - nothing will be run past this point */
    launch<GSG>()
}

suspend fun internetAndVersions(internetAccess: InternetAccess) = coroutineScope {
    launch {
        internetAccess.internetAccess

//        if (internetAccess.internetStatus) {
//            DownloadVersion.getVersions("HLA")
//        }
    }
}

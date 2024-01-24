package org.b12x.gfe

import javafx.stage.Stage
import kotlinx.coroutines.*
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.model.datadownload.version.DownloadVersion
import org.b12x.gfe.core.view.MainView
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.*

//class GSG : App(MainView::class, Styles::class) {
//
//    private val gsgWidth = 1150.0
//    private val gsgHeight = 800.0
//
//    override fun start(stage: Stage) {
//        super.start(stage)
//        stage.width = gsgWidth
//        stage.height = gsgHeight
////        stage.setOnHiding { stop() }
//
//        /* Internet Access */
//        val internetAccess = InternetAccess()
//        internetAccess.printResults()
//    }
//}
@OptIn(DelicateCoroutinesApi::class)
fun main() {

    /* Prefs Reset */
//    PrefsCore.nuclearOption()
    println(PrefsGfeSearch.currentGfeSearchLocusHla)

    /* Internet Access */
    val internetAccess = InternetAccess()

//    GlobalScope.async {
//        internetAndVersions(internetAccess)
//    }

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

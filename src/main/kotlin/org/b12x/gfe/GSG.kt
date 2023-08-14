package org.b12x.gfe

import javafx.stage.Stage
import org.b12x.gfe.core.view.MainView
import org.b12x.gfe.plugins.gfesearch.controller.eventbus.SearchBoxController
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchButtonSubmit.find
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.App

class GSG : App(MainView::class, Styles::class) {

    init {
        // Eagerly load CustomerController so it can receive events
        find(SearchBoxController::class)
    }

    private val gsgWidth = 1150.0
    private val gsgHeight = 800.0

    override fun start(stage: Stage) {
        stage.setOnHiding { stop() }
        super.start(stage)
        stage.width = gsgWidth
        stage.height = gsgHeight

        /* Internet Access */
//        var internetAccess = InternetAccess()
//        InternetAccess.isInternetAvailable()
    }
}
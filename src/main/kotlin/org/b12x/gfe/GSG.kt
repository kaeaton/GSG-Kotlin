package org.b12x.gfe

import javafx.stage.Stage
import org.b12x.gfe.core.view.MainView
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.*

class GSG : App(MainView::class, Styles::class) {

    private val gsgWidth = 1150.0
    private val gsgHeight = 800.0

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = gsgWidth
        stage.height = gsgHeight
//        stage.setOnHiding { stop() }

        /* Internet Access */
        val internetAccess = InternetAccess()
        internetAccess.printResults()
    }
}


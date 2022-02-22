package org.b12x.gfe

import javafx.stage.Stage
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.core.view.MainView
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextBox
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.App
import tornadofx.find

class GSG: App(MainView::class, Styles::class) {

    private val gsgWidth = 1150.0
    private val gsgHeight = 800.0

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = gsgWidth
        stage.height = gsgHeight
    }
}
package org.b12x.gfe.utilities

import io.ktor.client.*
import io.ktor.client.request.*
import javafx.embed.swing.JFXPanel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextBox
import tornadofx.find

object InternetAccess {

    private const val YES_INTERNET_MSG = "Internet access available.\n"
    private const val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"

    var internetAccess: Boolean = false

    fun isInternetAvailable(): Boolean {

        // necessary to initialize JavaFX
        val jfxPanel = JFXPanel()

        return runBlocking {
            try {
                ktorHttpClient.head<String>("http://google.com")
                GfeSearchInformationTextBox.infoTextArea.appendText(YES_INTERNET_MSG)
                true
            } catch (e: Exception) {
                println(e.message)
                GfeSearchInformationTextBox.infoTextArea.appendText(NO_INTERNET_MSG)

                false
            }
        }
    }

    val ktorHttpClient = HttpClient {}
}
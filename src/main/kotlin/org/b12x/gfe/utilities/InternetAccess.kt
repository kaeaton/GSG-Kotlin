package org.b12x.gfe.utilities

import io.ktor.client.*
import io.ktor.client.request.*
import javafx.embed.swing.JFXPanel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextBox
import tornadofx.find

object InternetAccess {

    private val YES_INTERNET_MSG = "Internet access available.\n"
    private val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"

    var internetAccess: Boolean = false

    fun isInternetAvailable(): Boolean {

        // necessary to initialize JavaFX
        val jfxPanel = JFXPanel()

        val gfeSearchInformationTextBox = find(GfeSearchInformationTextBox::class)

        return runBlocking {
            try {
                ktorHttpClient.head<String>("https://google.com")
                gfeSearchInformationTextBox.infoTextArea.appendText(YES_INTERNET_MSG)
                true
            } catch (e: Exception) {
                println(e.message)
                gfeSearchInformationTextBox.infoTextArea.appendText(NO_INTERNET_MSG)

                false
            }
        }
    }

    val ktorHttpClient = HttpClient {}
}
package org.b12x.gfe.utilities

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextArea
import tornadofx.find

class InternetAccess {

    private val YES_INTERNET_MSG = "Internet access available.\n"
    private val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"

    var internetAccess: Boolean = false

    fun isInternetAvailable(): Boolean {

        // necessary to initialize JavaFX
//        val jfxPanel = JFXPanel()

        val gfeSearchInformationTextArea = find(GfeSearchInformationTextArea::class)

        return runBlocking {
            try {
                ktorHttpClient.head<String>("https://google.com")
                gfeSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
                true
            } catch (e: Exception) {
                println(e.message)
                gfeSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)

                false
            }
        }
    }

    val ktorHttpClient = HttpClient {}
}
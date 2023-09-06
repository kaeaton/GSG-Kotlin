package org.b12x.gfe.utilities

import kotlinx.coroutines.*
import org.b12x.gfe.core.view.debugtab.DebugView
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import tornadofx.find
import java.net.HttpURLConnection
import java.net.URL

class InternetAccess {

    private val YES_INTERNET_MSG = "Internet access available.\n"
    private val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"
//    val gfeInformationTextArea = find(GfeTextAreaInfo::class)
//    val nameInformationTextArea = find(NameSearchInformationTextArea::class)
    var internetStatus = false
    val internetAccess = runBlocking {
        checkForGoogle()
    }

    /**
     * Determines if the program can reach Google (has internet) in
     * a separate coroutine. It prints the results to the info text areas.
     */
    suspend fun checkForGoogle() = coroutineScope {
        launch {

            var response = 0

            try {
                val url = URL("https://google.com")
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 3000
                connection.connect()

                response = connection.responseCode
                println(response)

                if (response == 200) {
//                    gfeInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                    nameInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
                    internetStatus = true

                }
            } catch (e: Exception) {
//                gfeInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
//                nameInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
                internetStatus = false
            }
        }
    }

    fun printResults() {
        val gfeInformationTextArea = find(GfeTextAreaInfo::class)
        val nameInformationTextArea = find(NameSearchInformationTextArea::class)

        if(internetStatus) {
            gfeInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
            nameInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
        } else {
            gfeInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
            nameInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
        }
    }
}


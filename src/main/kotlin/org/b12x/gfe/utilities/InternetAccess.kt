package org.b12x.gfe.utilities

import kotlinx.coroutines.*
import org.b12x.gfe.core.view.debugtab.DebugView
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import tornadofx.find
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class InternetAccess {

    private val YES_INTERNET_MSG = "Internet access available.\n"
    private val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"
    val gfeInformationTextArea = find(GfeTextAreaInfo::class)
    val nameInformationTextArea = find(NameSearchInformationTextArea::class)
    val debugViewTextArea = find(DebugView::class).debuggerTextArea

    val internetAccess = runBlocking {
        checkForGoogle()
    }

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
                    gfeInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
                    nameInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                    debugViewTextArea.debuggerTextArea.appendText(YES_INTERNET_MSG)
                }

            } catch (e: Exception) {
                gfeInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
                nameInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)

                // Handle exceptions
                when (e) {
                    is MalformedURLException -> "loadLink: Invalid URL ${e.message}"
                    is IOException -> "loadLink: IO Exception reading data: ${e.message}"
                    is SecurityException -> {
                        e.printStackTrace()
                        "loadLink: Security Exception. Needs permission? ${e.message}"
                    }

                    else -> "Unknown error: ${e.message}"
                }
            }
        }
    }
}


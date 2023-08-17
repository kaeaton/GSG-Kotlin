package org.b12x.gfe.utilities

//import io.ktor.client.request.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javafx.embed.swing.JFXPanel
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextArea
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import tornadofx.find
import tornadofx.runAsync
import java.io.IOException
import java.lang.invoke.ConstantBootstraps.invoke
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.reflect.KParameter

class InternetAccess() {

    private val YES_INTERNET_MSG = "Internet access available.\n"
    private val NO_INTERNET_MSG = "Internet access not available, locally available data only.\n"
    lateinit var internetAccess: Observer<Boolean>
//    var internetAccess: Boolean = isInternetAvailable()
    init {

    /**
     * Observer of internet status
     */
    var internetAccess: Observer<Boolean> = object : Observer<Boolean> {
        override fun onComplete() {
            println("onComplete: " + YES_INTERNET_MSG)
            GfeSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
        }

        override fun onNext(t: Boolean) {
            println("OnNext triggered")
        }

        override fun onError(e: Throwable) {
            println("onError: " + NO_INTERNET_MSG)
            GfeSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
        }

        override fun onSubscribe(d: Disposable) {
            println("There's a subscriber! Yay!")
        }
    }

//    val observable: Observable<Boolean> = Observable.create<Boolean> {
//        isInternetAvailable
//    }

    /**
     * Currently only works the first time. A change in internet status cannot be detected.
     */
    val isInternetAvailable: Observable<Boolean> = Observable.create<Boolean> {

        // necessary to initialize JavaFX
//        val jfxPanel = JFXPanel()

        val gfeSearchInformationTextArea = find(GfeSearchInformationTextArea::class)
        val nameSearchInformationTextArea = find(NameSearchInformationTextArea::class)

        runBlocking {
//            val ktorHttpClient = HttpClient(CIO){}
//            try {
////                ktorHttpClient.get("https://google.com")
//                GfeSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                NameSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                true
//            } catch (e: Exception) {
//                println(e.message)
//                GfeSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
//                NameSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
//                false
//            }
//            ktorHttpClient.close()
            var response = 0

            try {
                val url = URL("https://google.com")
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 3000
                connection.connect()

                response = connection.responseCode
                println(response)

            } catch (e: Exception) {
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

            // 200 for success
//            if (response == 200) {
//                GfeSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                NameSearchInformationTextArea.infoTextArea.appendText(YES_INTERNET_MSG)
//                println("We made it online")
//                true
//            } else {
//                GfeSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
//                NameSearchInformationTextArea.infoTextArea.appendText(NO_INTERNET_MSG)
//                println("Nope, still no internet")
//                false
//            }
        }
    }

    isInternetAvailable.subscribe(internetAccess)
}
}

package org.b12x.gfe.plugins.optionstab.view

import io.ktor.client.request.*
import javafx.embed.swing.JFXPanel
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.GSG
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.model.DataDownload
import org.b12x.gfe.core.model.parsers.ParserVersionData
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchInformationTextArea
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import org.b12x.gfe.utilities.InternetAccess
import org.b12x.gfe.utilities.InternetAccess.ktorHttpClient
import tornadofx.*
import java.util.prefs.Preferences

class OptionsView : View("My View") {



    val buttons = vbox {

        button ("Reset Preferences"){
            action {
                PrefsCore.nuclearOption()
            }
        }

        button ("Check for internet") {
            action {
//                val internetAccess = InternetAccess()
//                internetAccess.internetAccess = InternetAccess.isInternetAvailable()
                println("Options check: internet access? " + InternetAccess.internetAccess)
            }
        }

        button ("Download current Versions"){
            action {
                val hlaDataDownload = DataDownload("HLA")
                hlaDataDownload.makeRequest(dataType = "version", dataUrl = ParserVersionData.DB_VERSIONS)
            }
        }

        button ("Ping the data server"){
            action {
                val YES_SERVER_MSG = "The server is available.\n"
                val NO_SERVER_MSG = "The server is not available, locally available data only.\n"

                runBlocking {
                    try {
                        ktorHttpClient.head<String>("http://gfe.b12x.org/v1/imgt-versions")
                        infoTextArea.appendText(YES_SERVER_MSG)
                        GfeSearchInformationTextArea.infoTextArea.appendText(YES_SERVER_MSG)
                        NameSearchInformationTextArea.infoTextArea.appendText(YES_SERVER_MSG)
                    } catch (e: Exception) {
                        println(e.message)
                        infoTextArea.appendText(NO_SERVER_MSG)
                        GfeSearchInformationTextArea.infoTextArea.appendText(NO_SERVER_MSG)
                        NameSearchInformationTextArea.infoTextArea.appendText(NO_SERVER_MSG)
                    }
                }
            }
        }
    }

    val infoTextArea = textarea {
        style {
            fontSize = Dimension(1.1, Dimension.LinearUnits.em)
            prefWidth = Dimension(425.0, Dimension.LinearUnits.px)
            prefHeight = Dimension(350.0, Dimension.LinearUnits.px)
        }
    }

    override val root = hbox {
        add(buttons)
        add(infoTextArea)

        style {
            padding = box(50.px)
        }

        hboxConstraints {
            marginRight = 20.0
        }
    }
}

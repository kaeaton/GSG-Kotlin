package org.b12x.gfe.plugins.optionstab.view

import kotlinx.coroutines.runBlocking
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.model.datadownload.DataDownload
import org.b12x.gfe.core.model.parsers.ParserVersionData
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import org.b12x.gfe.utilities.InternetAccess
//import org.b12x.gfe.utilities.InternetAccess.ktorHttpClient
import tornadofx.*
import tornadofx.find

class OptionsView : View("My View") {


    val internetAccess = InternetAccess()
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
                println("Options check: internet access? " + InternetAccess().internetAccess)
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

                val access = internetAccess.internetAccess
//                val YES_SERVER_MSG = "The server is available.\n"
//                val NO_SERVER_MSG = "The server is not available, locally available data only.\n"
//
//                val gfeSearchInformationTextArea = tornadofx.find(GfeTextAreaInfo::class)
//                val nameSearchInformationTextArea = tornadofx.find(NameSearchInformationTextArea::class)
//
//                runBlocking {
//                    try {
////                        ktorHttpClient.head("http://gfe.b12x.org/v1/imgt-versions")
//                        infoTextArea.appendText(YES_SERVER_MSG)
//                        gfeSearchInformationTextArea.infoTextArea.appendText(YES_SERVER_MSG)
//                        nameSearchInformationTextArea.infoTextArea.appendText(YES_SERVER_MSG)
//                    } catch (e: Exception) {
//                        println(e.message)
//                        infoTextArea.appendText(NO_SERVER_MSG)
//                        gfeSearchInformationTextArea.infoTextArea.appendText(NO_SERVER_MSG)
//                        nameSearchInformationTextArea.infoTextArea.appendText(NO_SERVER_MSG)
//                    }
//                }
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

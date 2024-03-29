package org.b12x.gfe.core.view.debugtab

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import kotlinx.coroutines.*
import org.b12x.gfe.Styles
import org.b12x.gfe.core.model.datadownload.DataDownload
import org.b12x.gfe.core.model.datadownload.version.DownloadVersion
import tornadofx.*
import kotlin.system.exitProcess
import org.b12x.gfe.core.model.datadownload.gfes.DownloadGfes

class DebugView : View("Debug") {

    private val calcOptions = FXCollections.observableArrayList("Enumerated", "Sealed")
    private val selectedCalc = SimpleStringProperty(calcOptions.get(0))
    val download = DataDownload(lociGroup = "HLA-DRB1")
    val debuggerTextArea = textarea() {

    }

    suspend fun downloadVersions() {
        coroutineScope {
            async(Dispatchers.Default) {
//                            launch {
                DownloadVersion.getVersions("HLA")
            }
        }
    }

    override val root = stackpane {
        borderpane {

            top = hbox {
                button("Download Versions") {
                    action {
                        runBlocking {
                            launch {
                                downloadVersions()
                            }
                        }
                    }
                }

                button("Download GFEs") {
                    action {
                        runBlocking {
                            launch {
                                DownloadGfes.getGfes()
                            }
                        }
                    }
                }
            }

            center = vbox {
                add(debuggerTextArea)
            }

            bottom = hbox {
                add(button("Exit") {
                    setOnAction { exitProcess(0) }
                })
                add(button("Do Something") {
                    setOnAction {
                        debuggerTextArea.appendText("Do Something!\n")
                        println("Do Something!")
                    }
                })
            }
        }
        addClass(Styles.textAreas)
    }
}

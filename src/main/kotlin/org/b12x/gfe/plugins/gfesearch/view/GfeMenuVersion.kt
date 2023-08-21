package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.core.view.MenuVersion
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeMenuVersion : View(), MenuVersion {

    private val stateContext = LociStateContextGfeSearch

    /* list of Versions */
    override var versionList: VersionList = VersionList(stateContext.loci)
    var versions: List<String> = versionList.allVersionNames
    override var versionsList = observableListOf(versions)

    /* selected Version */
    val currentVersionProperty = SimpleStringProperty(stateContext.version)
    override var currentVersion: String by currentVersionProperty

    /* choiceBox */
    override var menuVersion = choicebox<String>(currentVersionProperty, versionsList) {
        action {
            if (this.value != null) {
                stateContext.version = this.value
                stateContext.updateLocuses()
                GfeViewData.resetArraysHard()
                find(GfeViewParent::class).swapSearchBoxes()
            }
        }
    }

    override var root = hbox {
        add(menuVersion)
    }
}

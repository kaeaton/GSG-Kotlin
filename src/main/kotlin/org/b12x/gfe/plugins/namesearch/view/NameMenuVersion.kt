package org.b12x.gfe.plugins.namesearch.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.core.controller.locistate.LociStateContext
import org.b12x.gfe.core.view.MenuVersion
import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*

class NameMenuVersion : View(), MenuVersion {

    private val stateContext = LociStateContextNameSearch
    private val tabStateContext = LociStateContext

    /* list of Versions */
    var versions: List<String> = tabStateContext.versionList
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
            }
        }
    }

    override var root = hbox {
        add(menuVersion)
    }
}

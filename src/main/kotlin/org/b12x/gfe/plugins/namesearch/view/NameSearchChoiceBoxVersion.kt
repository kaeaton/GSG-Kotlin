package org.b12x.gfe.plugins.namesearch.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.core.view.ComboBoxVersion
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*

class NameSearchChoiceBoxVersion : View(), ComboBoxVersion {

    private val stateContext = LociStateContextNameSearch

    /* list of Versions */
    override var versionList: VersionList = VersionList(stateContext.loci)
    var versions: List<String> = versionList.allVersionNames
    override var versionsList = observableListOf(versions)

    /* selected Version */
    val currentVersionProperty = SimpleStringProperty(stateContext.version)
    override var currentVersion: String by currentVersionProperty

    /* choiceBox */
    override var choiceBoxVersion = choicebox<String>(currentVersionProperty, versionsList) {
        action {
            if (this.value != null) {
                stateContext.version = this.value

                stateContext.updateLocuses()
            }
        }
    }

    override var root = hbox {
        add(choiceBoxVersion)
    }
}

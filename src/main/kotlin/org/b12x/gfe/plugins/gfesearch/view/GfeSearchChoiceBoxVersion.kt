package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.core.view.ComboBoxVersion
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*
import tornadofx.Stylesheet.Companion.empty

class GfeSearchChoiceBoxVersion : View("GFE Search Version Choice box"), ComboBoxVersion {

    private val stateContext = GfeSearchLayoutData.lociStateContextGfeSearch

    override var versionList: VersionList = VersionList(stateContext.loci.toString())
    var versions: List<String> = versionList.allVersionNames
    override var versionsObservableList: ObservableList<String> = FXCollections.observableArrayList(versions)

    private val currentVersionProperty = SimpleStringProperty(stateContext.getCurrentVersion())
    override var currentVersion: String by currentVersionProperty

    override var choiceBoxVersion = choicebox<String>(currentVersionProperty, versionsObservableList) {
        action {
            if (this.value != null) {
                stateContext.setCurrentVersion(this.value)
                GfeSearchLayoutData.currentVersion = this.value
                currentVersion = this.value
                stateContext.updateLocuses()

            }
        }
    }

    override var root = hbox {
        add(choiceBoxVersion)
    }


}

package org.b12x.gfe.core.view
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox
import org.b12x.gfe.core.controller.version.VersionList

interface ComboBoxVersion {
    var versionList: VersionList
    val versionsList: ObservableList<String>
    val currentVersion: String
    val choiceBoxVersion: ChoiceBox<String>
}
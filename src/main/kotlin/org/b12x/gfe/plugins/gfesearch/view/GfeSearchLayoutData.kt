package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
//import org.b12x.gfe.utilities.getLocusType
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import tornadofx.find
import tornadofx.property

class GfeSearchLayoutData {

    companion object {
        var checkList: MutableList<CheckBox> = ArrayList()
        var textList: MutableList<TextField> = ArrayList()

        var lociStateContextGfeSearch = LociStateContextGfeSearch()
        var currentVersionList: ArrayList<Version>
        init {
            val localVersions = LocalVersions(lociStateContextGfeSearch.getLoci())
            currentVersionList = localVersions.createVersions()
        }
        var currentVersion = find(GfeSearchChoiceBoxVersion::class).currentVersion

        fun resetArraysHard() {
            checkList = ArrayList()
            textList = ArrayList()
        }

        fun resetArraysSoft() {
            checkList.forEach { it.isSelected = false }
            textList.forEach { it.textProperty().value = "" }
            textList[0].textProperty().value = "w"
        }
    }
}

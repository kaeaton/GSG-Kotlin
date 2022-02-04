package org.b12x.gfe.plugins.gfesearch.view

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
//import org.b12x.gfe.utilities.getLocusType

class GfeSearchLayoutData {

    companion object {
        /* State Context */
        var lociStateContextGfeSearch = LociStateContextGfeSearch()

        /* Loci */
        var currentLoci: String = lociStateContextGfeSearch.getLoci()
        fun updateLoci(loci: String) {
            currentLoci = loci
            lociStateContextGfeSearch.setState(loci)
        }

        /* Version */
        var currentVersion: String = lociStateContextGfeSearch.getCurrentVersion()

        var currentVersionList: ArrayList<Version>
        init {
            val localVersions = LocalVersions(currentLoci)
            currentVersionList = localVersions.createVersions()
        }

        lateinit var currentVersionObject: Version
        init {
            currentVersionList.forEach {
                if (it.name == currentVersion) {
                    currentVersionObject = it
                }
            }
        }

        /* Locus */
        var currentLocusEnum: LociEnum = lociStateContextGfeSearch.getCurrentLocus()
        var currentLocus: String = currentLocusEnum.toString()
        var currentLocusList: List<String> = currentVersionObject.locusAvailable

        fun updateLocus(incomingCurrentLocus: String) {
            lociStateContextGfeSearch.setCurrentLocus(incomingCurrentLocus)
            currentLocusEnum = lociStateContextGfeSearch.getCurrentLocus()
            currentLocus = currentLocusEnum.toString()
        }

        /* Search Array Data */
        var checkList: MutableList<CheckBox> = ArrayList()
        var textList: MutableList<TextField> = ArrayList()

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

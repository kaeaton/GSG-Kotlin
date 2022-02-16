package org.b12x.gfe.plugins.gfesearch.view

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch

object GfeSearchLayoutData {

    /* State Context */
    var lociStateContextGfeSearch = LociStateContextGfeSearch()

    /* Loci */
    var currentLoci: String = lociStateContextGfeSearch.getLoci()
    fun updateLoci(loci: String) {
        currentLoci = loci
        lociStateContextGfeSearch.setState(loci)
        PrefsGfeSearch.currentGfeSearchLociGroup = loci
    }

    /* Version */
    var currentVersion: String = lociStateContextGfeSearch.getCurrentVersion()

    var currentVersionList: ArrayList<Version>

    init {
        var localVersions = LocalVersions(currentLoci)
        currentVersionList = localVersions.createVersions()

    }

    var currentVersionObject: Version = findCurrentVersionObject(currentVersion)

    fun findCurrentVersionObject(version: String): Version {
        lateinit var versionObject: Version
        currentVersionList.forEach {
            if (it.name == currentVersion) {
                versionObject = it
            }
        }
        return versionObject
    }

    fun updateVersions(version: String) {
        currentVersion = version
        lociStateContextGfeSearch.setCurrentVersion(currentVersion)
        lociStateContextGfeSearch.updateVersions()
        val localVersions = LocalVersions(currentLoci)
        currentVersionList = localVersions.createVersions()
        currentVersionObject = findCurrentVersionObject(currentVersion)
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

    /* Output Files*/
    var textFormat = "CVS" // Add to state
    var writeToFile = false // Add to state

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

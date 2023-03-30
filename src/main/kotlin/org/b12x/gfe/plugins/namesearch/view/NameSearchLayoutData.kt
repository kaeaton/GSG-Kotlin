package org.b12x.gfe.plugins.namesearch.view

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import java.io.File

object NameSearchLayoutData {

    /* State Context */
//    var stateContext = LociStateContextGfeSearch

    /* Loci */
//    var currentLoci: String = stateContext.loci.toString()
//    fun updateLoci(loci: String) {
//        currentLoci = loci
//        stateContext.setState(loci)
//        PrefsGfeSearch.currentGfeSearchLociGroup = loci
//    }

    /* Version */
//    var currentVersion: String = stateContext.version
//
//    var currentVersionList: ArrayList<Version>
//
//    init {
//        var localVersions = LocalVersions(currentLoci)
//        currentVersionList = localVersions.createVersions()
//    }
//
//    var currentVersionObject: Version = findCurrentVersionObject(currentVersion)
//
//    fun findCurrentVersionObject(version: String): Version {
////        lateinit var versionObject: Version
//
//        val userDirectory = System.getProperty("user.home")
//        return Version(
//            File("${userDirectory}/Documents/GSG/GSGData/HLA/2.0.0/"),
//            "2.0.0",
//            locusAvailable = listOf("HLA-A", "HLA-C", "HLA-DQB1")
//        )
//
////        currentVersionList.forEach {
////            if (it.name == currentVersion) {
////                versionObject = it
////            }
////        }
//
////        return versionObject
//    }
//
//    fun updateVersions(version: String) {
//        currentVersion = version
//        stateContext.version = currentVersion
//        stateContext.updateVersions()
//        val localVersions = LocalVersions(currentLoci)
//        currentVersionList = localVersions.createVersions()
//        currentVersionObject = findCurrentVersionObject(currentVersion)
//    }

    /* Locus */
//    var currentLocusEnum: LociEnum = stateContext.locusEnum
//    var currentLocus: String = stateContext.locus
////    var currentLocusList: List<String> = currentVersionObject.locusAvailable
//
//    fun updateLocus(incomingCurrentLocus: String) {
//        stateContext.locus = incomingCurrentLocus
//        currentLocus = stateContext.locus
//    }

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

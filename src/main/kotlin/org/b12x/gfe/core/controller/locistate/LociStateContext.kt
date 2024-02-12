package org.b12x.gfe.core.controller.locistate

import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import kotlin.properties.Delegates

object LociStateContext {
    private var currentState: LociState? = null

    init {
        currentState = when ("GFE") { //Prefs.currentTab) {
            "GFE" -> GfeSearchState()
            "NAME" -> NameSearchState()
            "COMP" -> ComparisonState()
            else -> GfeSearchState()
        }
    }

    fun setState(tab: String) {
        currentState = when (tab) {
            "GFE" -> GfeSearchState()
            "NAME" -> NameSearchState()
            "COMP" -> ComparisonState()
            "OPT" -> OptionState()
            else -> GfeSearchState()
        }
        println("Current Tab State: ${currentState.toString()}")
    }

    /* Which tab */
    fun getTab(): String {
        return currentState?.getTab(this)
            ?: GfeSearchState().getTab(this)
    }

    /* Loci */
//    var loci: String by Delegates.observable(currentState?.loci.toString()) { _, _, newValue ->
//        println("LociState: loci: ${newValue}")
//        LociStateContext.currentState?.loci = newValue
//    }

    var loci: String by Delegates.observable(PrefsLociState.currentLociGroup)
    { _, _, newValue ->
        PrefsLociState.currentLociGroup = newValue
    }

    /* Version */
    var version: String by Delegates.observable(currentState?.version.toString()) { _, _, newValue ->
        println("LociState: version: ${newValue}")
        currentState?.version = newValue
    }

    var versionObject: Version by Delegates.observable(currentState?.versionObject as Version) { _, _, newValue ->
        currentState?.versionObject = newValue
    }

//    fun getCurrentVersion() =
//        currentState?.getCurrentVersion(this)
//            ?: GfeSearchState().getCurrentVersion(this)

    var versionList: List<String> by Delegates.observable(VersionList("HLA").allVersionNames) {
            _, _, _ ->
    }
}
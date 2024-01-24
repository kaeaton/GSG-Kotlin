package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import kotlin.properties.Delegates

object TabStateContext {
    private var currentState: TabState? = null

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
//        println("TabState: loci: ${newValue}")
//        TabStateContext.currentState?.loci = newValue
//    }

    var loci: String by Delegates.observable(PrefsTabSearch.currentLociGroup)
    { _, _, newValue ->
        PrefsTabSearch.currentLociGroup = newValue
    }

    /* Version */
    var version: String by Delegates.observable(currentState?.version.toString()) { _, _, newValue ->
        println("TabState: version: ${newValue}")
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
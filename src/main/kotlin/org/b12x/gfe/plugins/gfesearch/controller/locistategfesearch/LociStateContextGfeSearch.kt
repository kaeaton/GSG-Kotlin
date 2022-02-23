package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import tornadofx.View
import kotlin.properties.Delegates

object LociStateContextGfeSearch {

    /* Loci */
    var loci: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLociGroup) { _, oldValue, newValue ->
        PrefsGfeSearch.currentGfeSearchLociGroup = newValue
    }

    /* What state? */
    private var currentState: LociStateGfeSearch? = null

    init {
        currentState = when (loci) {
            "HLA" -> HlaStateGfeSearch
            "KIR" -> KirStateGfeSearch
            else -> HlaStateGfeSearch
        }
    }

    fun setState(loci: String) {
        currentState = when (loci) {
            "HLA" -> HlaStateGfeSearch
            "KIR" -> KirStateGfeSearch
            else -> HlaStateGfeSearch
        }
    }

    /* Version */

    fun getCurrentVersion() =
        currentState?.getCurrentVersion(this)
            ?: HlaStateGfeSearch.getCurrentVersion(this)

    fun setCurrentVersion(currentVersion: String) =
        currentState?.setCurrentVersion(this, currentVersion)
            ?: HlaStateGfeSearch.setCurrentVersion(this, currentVersion)

    fun updateVersions() =
        currentState?.updateVersions(this)
            ?: HlaStateGfeSearch.updateVersions(this)

    /* Locus */

    fun getCurrentLocus() =
        currentState?.getCurrentLocus(this)
            ?: HlaStateGfeSearch.getCurrentLocus(this)

    fun setCurrentLocus(currentLocus: String) =
        currentState?.setCurrentLocus(this, currentLocus)
            ?: HlaStateGfeSearch.setCurrentLocus(this, currentLocus)

    fun updateLocuses() =
        currentState?.updateLocuses(this)
            ?: HlaStateGfeSearch.updateLocuses(this)

    fun createNewSearchBoxes() =
        currentState?.createNewSearchBoxes(this)
            ?: HlaStateGfeSearch.createNewSearchBoxes(this)
}

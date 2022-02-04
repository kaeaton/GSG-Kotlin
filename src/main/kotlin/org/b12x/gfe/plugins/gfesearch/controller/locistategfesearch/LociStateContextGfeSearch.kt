package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import tornadofx.View

class LociStateContextGfeSearch {
    private var currentState: LociStateGfeSearch? = null

    init {
        currentState = when (PrefsGfeSearch.currentGfeSearchLociGroup) {
            "HLA" -> HlaStateGfeSearch()
            "KIR" -> KirStateGfeSearch()
            else -> HlaStateGfeSearch()
        }
    }

    fun setState(loci: String) {
        currentState = when (loci) {
            "HLA" -> HlaStateGfeSearch()
            "KIR" -> KirStateGfeSearch()
            else -> HlaStateGfeSearch()
        }
    }

    /* Loci */

    fun getLoci() =
        currentState?.getLoci(this)
            ?: HlaStateGfeSearch().getLoci(this)

    /* Version */

    fun getCurrentVersion() =
        currentState?.getCurrentVersion(this)
            ?: HlaStateGfeSearch().getCurrentVersion(this)

    fun setCurrentVersion(currentVersion: String) =
        currentState?.setCurrentVersion(this, currentVersion)
            ?: HlaStateGfeSearch().setCurrentVersion(this, currentVersion)

    fun updateVersions() =
        currentState?.updateVersions(this)
            ?: HlaStateGfeSearch().updateVersions(this)

    /* Locus */

    fun getCurrentLocus() =
        currentState?.getCurrentLocus(this)
            ?: HlaStateGfeSearch().getCurrentLocus(this)

    fun setCurrentLocus(currentLocus: String) =
        currentState?.setCurrentLocus(this, currentLocus)
            ?: HlaStateGfeSearch().setCurrentLocus(this, currentLocus)

    fun updateLocuses(currentVersion: String) =
        currentState?.updateLocuses(this, currentVersion)
            ?: HlaStateGfeSearch().updateLocuses(this, currentVersion)

    fun getInitialLocusNamesList() =
        currentState?.getInitialLocusNamesList(this)
            ?: HlaStateGfeSearch().getInitialLocusNamesList(this)

    fun createNewSearchBoxes() =
        currentState?.createNewSearchBoxes(this)
            ?: HlaStateGfeSearch().createNewSearchBoxes(this)
}

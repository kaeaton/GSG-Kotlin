package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import kotlin.properties.Delegates

object LociStateContextNameSearch {

    /* Loci */
    var loci: String by Delegates.observable(PrefsNameSearch.currentNameSearchLociGroup) { _, _, newValue ->
        PrefsNameSearch.currentNameSearchLociGroup = newValue
    }

    /* What state? */
    private var currentState: LociStateNameSearch? = null

    init {
        currentState = when (loci) {
            "HLA" -> HlaStateNameSearch()
            "KIR" -> KirStateNameSearch()
            else -> HlaStateNameSearch()
        }
    }

    fun setState(loci: String) {
        currentState = when (loci) {
            "HLA" -> HlaStateNameSearch()
            "KIR" -> KirStateNameSearch()
            else -> HlaStateNameSearch()
        }
    }

    /* Version */

    var version: String by Delegates.observable(currentState?.version.toString()) {
        _, _, newValue -> currentState?.version = newValue
    }

    var versionObject: Version by Delegates.observable(currentState?.versionObject as Version) {
        _, _, newValue -> currentState?.versionObject = newValue}

    fun updateVersions() = currentState?.updateVersions(this) ?: HlaStateNameSearch().updateVersions(this)

    /* Locus */

    var locus: String by Delegates.observable(currentState?.locus.toString()) {
        _, _, newValue -> currentState?.locus = newValue
    }

    var locusEnum: LociEnum by Delegates.observable((currentState?.locusEnum) as LociEnum) {
        _, _, newValue -> currentState?.locusEnum = newValue
    }

    fun updateLocuses() = currentState?.updateLocuses(this) ?: HlaStateNameSearch().updateLocuses(this)
}

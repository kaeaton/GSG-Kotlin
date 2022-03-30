package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

object LociStateContextGfeSearch {

    /* Loci */
    var loci: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLociGroup) { _, _, newValue ->
        PrefsGfeSearch.currentGfeSearchLociGroup = newValue
    }

    /* What state? */
    private var currentState: LociStateGfeSearch? = null

    init {
        currentState = when (loci) {
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

    /* Version */

    var version: String by Delegates.observable(currentState?.version.toString()) { _, _, newValue ->
        currentState?.version = newValue
    }

    var versionObject: Version by Delegates.observable(currentState?.versionObject as Version) { _, _, _ -> }

    fun updateVersions() = currentState?.updateVersions(this) ?: HlaStateGfeSearch().updateVersions(this)

    /* Locus */

    var locus: String by Delegates.observable(currentState?.locus.toString()) { _, _, newValue ->
        currentState?.locus = newValue
    }

    var locusEnum: LociEnum by Delegates.observable((currentState?.locusEnum) as LociEnum) { _, _, _ ->

    }

    fun updateLocuses() = currentState?.updateLocuses(this) ?: HlaStateGfeSearch().updateLocuses(this)

    fun createNewSearchBoxes() =
        currentState?.createNewSearchBoxes(this) ?: HlaStateGfeSearch().createNewSearchBoxes(this)
}

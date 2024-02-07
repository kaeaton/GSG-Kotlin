package org.b12x.gfe.plugins.optionstab.controller.locistateoptions

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

object LociStateContextOptions {

    /* Loci */
    var loci: String by Delegates.observable(PrefsOptions.currentOptionsLociGroup) { _, _, newValue ->
        PrefsOptions.currentOptionsLociGroup = newValue
    }

    /* What state? */
    private var currentState: LociStateOptions? = null

    init {
        currentState = when (loci) {
            "HLA" -> HlaStateOptions()
            "KIR" -> KirStateOptions()
            else -> HlaStateOptions()
        }
    }

    fun setState(loci: String) {
        currentState = when (loci) {
            "HLA" -> HlaStateOptions()
            "KIR" -> KirStateOptions()
            else -> HlaStateOptions()
        }
    }

    /* Version */

    var version: String by Delegates.observable(currentState?.version.toString()) { _, _, newValue ->
        currentState?.version = newValue
    }

    var versionObject: Version by Delegates.observable(currentState?.versionObject as Version) { _, _, _ -> }

    fun updateVersions() = currentState?.updateVersions(this) ?: HlaStateOptions().updateVersions(this)

    /* Locus */

    var locus: String by Delegates.observable(currentState?.locus.toString()) { _, _, newValue ->
        currentState?.locus = newValue
    }

    var locusEnum: LociEnum by Delegates.observable((currentState?.locusEnum) as LociEnum) { _, _, _ ->

    }

    fun updateLocuses() = currentState?.updateLocuses(this) ?: HlaStateOptions().updateLocuses(this)
}

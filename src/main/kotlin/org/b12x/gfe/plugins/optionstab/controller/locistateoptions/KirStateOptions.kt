package org.b12x.gfe.plugins.optionstab.controller.locistateoptions

import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.CreateNewKirVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuVersion
import tornadofx.*
import kotlin.properties.Delegates

class KirStateOptions : LociStateOptions {

    /* Version */

    override var version: String by Delegates.observable(
        PrefsOptions.currentOptionsVersionKir
    ) { _, _, newValue -> PrefsOptions.currentOptionsVersionKir = newValue }

    override var versionObject: Version by Delegates.observable(
        CreateNewKirVersionObject.createVersionObject()
    ) { _, _, _ ->  CreateNewKirVersionObject.createVersionObject()}

    override fun updateVersions(ctx: LociStateContextOptions) {
        val gfeMenuVersion = find(GfeMenuVersion::class)

        gfeMenuVersion.versionsList.clear()
        gfeMenuVersion.versionsList.add("2.7.0")

        gfeMenuVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsOptions.currentOptionsLocusKir) { _, _, newValue ->
        PrefsOptions.currentOptionsLocusKir = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (KirLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> (KirLoci.values().find { it.fullName == locus }) as LociEnum }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextOptions) {
        val locusNames = versionObject.locusAvailable

        val gfeMenuLocus = find(GfeMenuLocus::class)
        val locObservableList = gfeMenuLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeMenuLocus.currentLocus = locus
    }
}

package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.CreateNewKirVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuVersion
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxes
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxesKir
import tornadofx.*
import kotlin.properties.Delegates

class KirStateGfeSearch : LociStateGfeSearch {

    /* Version */

    override var version: String by Delegates.observable(
        PrefsGfeSearch.currentGfeSearchVersionKir
    ) { _, _, newValue -> PrefsGfeSearch.currentGfeSearchVersionKir = newValue }

    override var versionObject: Version by Delegates.observable(
        CreateNewKirVersionObject.createVersionObject()
    ) { _, _, _ ->  CreateNewKirVersionObject.createVersionObject()}

    override fun updateVersions(ctx: LociStateContextGfeSearch) {
        val gfeMenuVersion = find(GfeMenuVersion::class)

        gfeMenuVersion.versionsList.clear()
        gfeMenuVersion.versionsList.add("2.7.0")

        gfeMenuVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLocusKir) { _, _, newValue ->
        PrefsGfeSearch.currentGfeSearchLocusKir = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (KirLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> (KirLoci.values().find { it.fullName == locus }) as LociEnum }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextGfeSearch) {
        val locusNames = versionObject.locusAvailable

        val gfeMenuLocus = find(GfeMenuLocus::class)
        val locObservableList = gfeMenuLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeMenuLocus.currentLocus = locus
    }

    override fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): GfeSearchBoxes {
//        val currentLocus = locusEnum
            // KirLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusKir } ?: KirLoci.KIR2DL1
        return GfeSearchBoxesKir(locusEnum) as GfeSearchBoxes
    }
}

package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.CreateNewVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxVersion
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesKir
import tornadofx.*
import kotlin.properties.Delegates

object KirStateGfeSearch : LociStateGfeSearch {

    /* Version */

    override var version: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchVersionKir) { _, oldValue, newValue ->
        PrefsGfeSearch.currentGfeSearchVersionKir = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewVersionObject.createVersionObject("KIR", HlaStateGfeSearch.version)
    ) { _, _, _ -> }

    override fun updateVersions(ctx: LociStateContextGfeSearch) {
        var versionList = VersionList("KIR")
        var versions = versionList.allVersionNames

        GfeSearchChoiceBoxVersion.versionsList.clear()
        GfeSearchChoiceBoxVersion.versionsList.addAll(versions)

        GfeSearchChoiceBoxVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLocusKir) { _, _, newValue ->
        PrefsGfeSearch.currentGfeSearchLocusKir = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (KirLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> }

    fun getKirLocusNames(): List<String> {
        val locusNames = ArrayList<String>()
        KirLoci.values().forEach {
            locusNames.add(it.toString())
        }
        return locusNames
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextGfeSearch) {
        val locusNames = getKirLocusNames()

        val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
        val locObservableList = gfeSearchChoiceBoxLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeSearchChoiceBoxLocus.currentLocus = PrefsGfeSearch.currentGfeSearchLocusKir
    }

    override fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): View {
        val currentLocus =
            KirLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusKir } ?: KirLoci.KIR2DL1
        return GfeSearchViewSearchBoxesKir(currentLocus)
    }
}

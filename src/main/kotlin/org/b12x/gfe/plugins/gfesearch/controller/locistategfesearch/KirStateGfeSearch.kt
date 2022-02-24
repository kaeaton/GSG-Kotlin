package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.KirLoci
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

    override fun updateVersions(ctx: LociStateContextGfeSearch) {
        var versionList = VersionList("KIR")
        var versions = versionList.allVersionNames

        GfeSearchChoiceBoxVersion.versionsList.clear()
        GfeSearchChoiceBoxVersion.versionsList.addAll(versions)

        GfeSearchChoiceBoxVersion.currentVersion = version
    }

    /* Locus */

    override fun getCurrentLocus(ctx: LociStateContextGfeSearch): KirLoci =
        KirLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusKir } ?: KirLoci.KIR2DL1

    override fun setCurrentLocus(ctx: LociStateContextGfeSearch, newLocus: String) {
        PrefsGfeSearch.currentGfeSearchLocusKir = newLocus
    }

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
        val locObservableList = gfeSearchChoiceBoxLocus.locusObservableList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeSearchChoiceBoxLocus.currentLocus = PrefsGfeSearch.currentGfeSearchLocusKir
    }

    override fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): View {
        val currentLocus = KirLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusKir } ?: KirLoci.KIR2DL1
        return GfeSearchViewSearchBoxesKir(currentLocus)
    }
}

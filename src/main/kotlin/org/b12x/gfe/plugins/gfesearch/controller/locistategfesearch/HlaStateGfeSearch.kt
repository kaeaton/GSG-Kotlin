package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxVersion
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesHla
import tornadofx.*


class HlaStateGfeSearch : LociStateGfeSearch {

    /* Loci */
    override fun getLoci(ctx: LociStateContextGfeSearch) = "HLA"

    /* Version */
    override fun getCurrentVersion(ctx: LociStateContextGfeSearch) =
        PrefsGfeSearch.currentGfeSearchVersionHla

    override fun setCurrentVersion(ctx: LociStateContextGfeSearch, newVersion: String) {
        PrefsGfeSearch.currentGfeSearchVersionHla = newVersion
    }

    override fun updateVersions(ctx: LociStateContextGfeSearch) {
        var versionList = VersionList("HLA")
        var versions = versionList.allVersionNames

        val gfeSearchComboBoxVersion = find(GfeSearchChoiceBoxVersion::class)
        val versionObservableList = gfeSearchComboBoxVersion.versionsObservableList
        versionObservableList.clear()
        versionObservableList.addAll(versions)

        gfeSearchComboBoxVersion.currentVersion = PrefsGfeSearch.currentGfeSearchVersionHla
    }

    /* Locus */
    override fun getCurrentLocus(ctx: LociStateContextGfeSearch): HlaLoci
        = HlaLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusHla } ?: HlaLoci.A

    override fun setCurrentLocus(ctx: LociStateContextGfeSearch, newLocus: String) {
        PrefsGfeSearch.currentGfeSearchLocusHla = newLocus
    }

    fun getHlaLocusNames(): List<String> {
        lateinit var locusNames: List<String>
        var versions = GfeSearchLayoutData.currentVersionList
        var version = GfeSearchLayoutData.currentVersion

        versions.forEach {
            if (it.name == version) {
                locusNames = it.locusAvailable
                println("potential locus name: ${it.locusAvailable}")
            }
        }

        return locusNames
    }

    override fun getCurrentLocusNamesList(ctx: LociStateContextGfeSearch) = getHlaLocusNames()

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextGfeSearch) {
        var locusNames = getHlaLocusNames()

        val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
        val locObservableList = gfeSearchChoiceBoxLocus.locusObservableList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeSearchChoiceBoxLocus.currentLocus = PrefsGfeSearch.currentGfeSearchLocusHla
    }

    override fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): View {
        val currentLocus = HlaLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusHla } ?: HlaLoci.A

        return GfeSearchViewSearchBoxesHla(currentLocus)
    }
}
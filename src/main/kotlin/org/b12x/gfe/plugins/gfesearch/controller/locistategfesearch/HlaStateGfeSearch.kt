package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.locus.LocusList
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxVersion
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesHla
import tornadofx.*
import kotlin.properties.Delegates


object HlaStateGfeSearch : LociStateGfeSearch {

    /* Loci */

//    override var loci: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLociGroup) {_, oldValue, newValue ->
//        PrefsGfeSearch.currentGfeSearchLociGroup = newValue
//    }

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
    override fun getCurrentLocus(ctx: LociStateContextGfeSearch): HlaLoci =
        HlaLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusHla } ?: HlaLoci.A

    override fun setCurrentLocus(ctx: LociStateContextGfeSearch, newLocus: String) {
        PrefsGfeSearch.currentGfeSearchLocusHla = newLocus
    }

    fun getHlaLocusNames(currentVersion: String): List<String> {
        val localVersions = LocalVersions("HLA")
        val versionList = localVersions.versionsList
//        val currentVersion = find(GfeSearchChoiceBoxVersion::class).currentVersion
        lateinit var currentVersionObject: Version

        versionList.forEach {
            if (it.name == currentVersion) {
                currentVersionObject = it
            }
        }

        val choiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
        val locusList = LocusList(currentVersionObject, choiceBoxLocus)
        locusList.updateLocusList()

        return locusList.newLocusList
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextGfeSearch) {
        val currentVersion = GfeSearchLayoutData.currentVersion
        var locusNames = getHlaLocusNames(currentVersion)

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
package org.b12x.gfe.plugins.optionstab.controller.locistateoptions

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.locus.LocusList
import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxVersion
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxes
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesHla
import tornadofx.*
import kotlin.properties.Delegates


class HlaStateOptions : LociStateOptions {

    /* Version */

    override var version: String by Delegates.observable(PrefsOptions.currentOptionsVersionHla) { _, oldValue, newValue ->
        PrefsOptions.currentOptionsVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ -> CreateNewHlaVersionObject.createVersionObject("HLA", version) }

    override fun updateVersions(ctx: LociStateContextOptions) {
        var versionList = VersionList("HLA")
        var versions = versionList.allVersionNames

        val gfeSearchChoiceBoxVersion = find(GfeSearchChoiceBoxVersion::class)

        gfeSearchChoiceBoxVersion.versionsList.clear()
        gfeSearchChoiceBoxVersion.versionsList.addAll(versions)

        gfeSearchChoiceBoxVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsOptions.currentOptionsLocusHla) { _, _, newValue ->
        PrefsOptions.currentOptionsLocusHla = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (HlaLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> (HlaLoci.values().find { it.fullName == locus }) as LociEnum }

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

        val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
        val locusList = LocusList(currentVersionObject, gfeSearchChoiceBoxLocus)
        locusList.updateLocusList()

        return locusList.newLocusList
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextOptions) {
        val currentVersion = version
        var locusNames = getHlaLocusNames(currentVersion)

        val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
        val locObservableList = gfeSearchChoiceBoxLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeSearchChoiceBoxLocus.currentLocus = locus //PrefsGfeSearch.currentGfeSearchLocusHla
    }
}
package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.locus.LocusList
import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.namesearch.view.NameSearchChoiceBoxLocus
import org.b12x.gfe.plugins.namesearch.view.NameSearchChoiceBoxVersion
import tornadofx.*
import kotlin.properties.Delegates


class HlaStateNameSearch : LociStateNameSearch {

    /* Version */

    override var version: String by Delegates.observable(PrefsNameSearch.currentNameSearchVersionHla) { _, oldValue, newValue ->
        PrefsNameSearch.currentNameSearchVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ -> CreateNewHlaVersionObject.createVersionObject("HLA", version) }

    override fun updateVersions(ctx: LociStateContextNameSearch) {
        var versionList = VersionList("HLA")
        var versions = versionList.allVersionNames

        val nameSearchChoiceBoxVersion = find(NameSearchChoiceBoxVersion::class)

        nameSearchChoiceBoxVersion.versionsList.clear()
        nameSearchChoiceBoxVersion.versionsList.addAll(versions)

        nameSearchChoiceBoxVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsNameSearch.currentNameSearchLocusHla) { _, _, newValue ->
        PrefsNameSearch.currentNameSearchLocusHla = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (HlaLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> (HlaLoci.values().find { it.fullName == locus }) as LociEnum }

    fun getHlaLocusNames(currentVersion: String): List<String> {
        val localVersions = LocalVersions("HLA")
        val versionList = localVersions.versionsList
//        val currentVersion = find(GfeMenuVersion::class).currentVersion
        lateinit var currentVersionObject: Version

        versionList.forEach {
            if (it.name == currentVersion) {
                currentVersionObject = it
            }
        }

        val nameSearchChoiceBoxLocus = find(NameSearchChoiceBoxLocus::class)
        val locusList = LocusList(currentVersionObject, nameSearchChoiceBoxLocus)
        locusList.updateLocusList()

        return locusList.newLocusList
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextNameSearch) {
        val currentVersion = version
        var locusNames = getHlaLocusNames(currentVersion)

        val nameSearchChoiceBoxLocus = find(NameSearchChoiceBoxLocus::class)
        val locObservableList = nameSearchChoiceBoxLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        nameSearchChoiceBoxLocus.currentLocus = locus //PrefsGfeSearch.currentGfeSearchLocusHla
    }
}
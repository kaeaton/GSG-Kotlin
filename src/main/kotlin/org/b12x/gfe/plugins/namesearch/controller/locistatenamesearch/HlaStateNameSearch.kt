package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.locus.LocusList
import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.namesearch.view.NameMenuLocus
import org.b12x.gfe.plugins.namesearch.view.NameMenuVersion
import tornadofx.*
import kotlin.properties.Delegates


class HlaStateNameSearch : LociStateNameSearch {

    /* Version */

    override var version: String by Delegates.observable(PrefsNameSearch.currentNameSearchVersionHla)
    { _, _, newValue ->
        PrefsNameSearch.currentNameSearchVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ -> }

    override fun updateVersions(ctx: LociStateContextNameSearch) {
        var versionList = VersionList("HLA")
        var versions = versionList.allVersionNames

        val nameMenuVersion = find(NameMenuVersion::class)

        nameMenuVersion.versionsList.clear()
        nameMenuVersion.versionsList.addAll(versions)

        nameMenuVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsNameSearch.currentNameSearchLocusHla)
    { _, _, newValue ->
        PrefsNameSearch.currentNameSearchLocusHla = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (HlaLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> }

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

        val nameMenuLocus = find(NameMenuLocus::class)
        val locusList = LocusList(currentVersionObject, nameMenuLocus)
        locusList.updateLocusList()

        return locusList.newLocusList
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextNameSearch) {
        val currentVersion = version
        var locusNames = getHlaLocusNames(currentVersion)

        val nameMenuLocus = find(NameMenuLocus::class)
        val locObservableList = nameMenuLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        nameMenuLocus.currentLocus = locObservableList.first()//PrefsGfeSearch.currentGfeSearchLocusHla
    }
}
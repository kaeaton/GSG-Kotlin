package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.locus.LocusList
import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.controller.version.VersionList
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeMenuVersion
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxes
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxesHla
import tornadofx.*
import kotlin.properties.Delegates
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable


class HlaStateGfeSearch : LociStateGfeSearch {

    /* Version */

    override var version: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchVersionHla) { _, oldValue, newValue ->
        PrefsGfeSearch.currentGfeSearchVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ -> CreateNewHlaVersionObject.createVersionObject("HLA", version) }

    override fun updateVersions(ctx: LociStateContextGfeSearch) {
        var versionList = VersionList("HLA")
        var versions = versionList.allVersionNames

        val gfeMenuVersion = find(GfeMenuVersion::class)

        gfeMenuVersion.versionsList.clear()
        gfeMenuVersion.versionsList.addAll(versions)

        gfeMenuVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsGfeSearch.currentGfeSearchLocusHla)
    { _, _, newValue ->
        locusEnum = (HlaLoci.values().find { it.fullName == newValue }) as LociEnum
        PrefsGfeSearch.currentGfeSearchLocusHla = newValue
    }

//    private val connectableLocusObservable: Observable<String> = locus.toObservable()

    override var locusEnum: LociEnum by Delegates.observable((HlaLoci.values().find { it.fullName == locus }) as LociEnum)
    { _, _, _ -> }
    //= (HlaLoci.values().find { it.fullName == locus }) as LociEnum
//        HlaLoci.values().find { newValue.fullName == locus } as LociEnum }


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

        val gfeMenuLocus = find(GfeMenuLocus::class)
        val locusList = LocusList(currentVersionObject, gfeMenuLocus)
        locusList.updateLocusList()

        return locusList.newLocusList
    }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextGfeSearch) {
        val currentVersion = version
        var locusNames = getHlaLocusNames(currentVersion)

        val gfeMenuLocus = find(GfeMenuLocus::class)
        val locObservableList = gfeMenuLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        gfeMenuLocus.currentLocus = locus //PrefsGfeSearch.currentGfeSearchLocusHla
    }

    override fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): GfeSearchBoxes {
//        val currentLocus = locusEnum
             HlaLoci.values().find { it.fullName == PrefsGfeSearch.currentGfeSearchLocusHla } ?: HlaLoci.A

        return GfeSearchBoxesHla(locusEnum) as GfeSearchBoxes
    }
}
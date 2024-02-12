package org.b12x.gfe.core.controller.locistate

import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

class GfeSearchState : LociState {

    // setting up so the core can identify which tab the version
    // request is coming from.
    // I can't remember why I care.

    // Because you only need to pull it once.
    // Each tab can use the same version list.
    // I ended up putting the version initialization
    // and generation in LociStateContext because
    // it doesn't need to be reinitialized for each tab.

    /* What tab */
    override fun getTab(ctx: LociStateContext) = "GFE"

    /* What Loci */

    var loci: String by Delegates.observable(PrefsLociState.currentLociGroup)
    { _, _, newValue ->
        PrefsLociState.currentLociGroup = newValue
    }

    /* Version */
//    override fun getCurrentVersion(ctx: LociStateContext): String {
//        return "version"
//    }

    override var version: String by Delegates.observable(PrefsLociState.currentVersionHla)
    { _, _, newValue ->
        PrefsLociState.currentVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ ->  }

//    override fun updateVersions(ctx: LociStateContextGfeSearch) {
//        var versionList = VersionList("HLA")
//        var versions = versionList.allVersionNames
//
//        val gfeMenuVersion = find(GfeMenuVersion::class)
//
//        gfeMenuVersion.versionsList.clear()
//        gfeMenuVersion.versionsList.addAll(versions)
//
//        gfeMenuVersion.currentVersion = version
//    }
}
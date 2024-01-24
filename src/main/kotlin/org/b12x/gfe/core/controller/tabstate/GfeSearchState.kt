package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

class GfeSearchState : TabState {

    // setting up so the core can identify which tab the version
    // request is coming from.
    // I can't remember why I care.

    // Because you only need to pull it once.
    // Each tab can use the same version list.
    // I ended up putting the version initialization
    // and generation in TabStateContext because
    // it doesn't need to be reinitialized for each tab.

    /* What tab */
    override fun getTab(ctx: TabStateContext) = "GFE"

    /* What Loci */

    var loci: String by Delegates.observable(PrefsTabSearch.currentLociGroup)
    { _, _, newValue ->
        PrefsTabSearch.currentLociGroup = newValue
    }

    /* Version */
//    override fun getCurrentVersion(ctx: TabStateContext): String {
//        return "version"
//    }

    override var version: String by Delegates.observable(PrefsTabSearch.currentVersionHla)
    { _, _, newValue ->
        PrefsTabSearch.currentVersionHla = newValue
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
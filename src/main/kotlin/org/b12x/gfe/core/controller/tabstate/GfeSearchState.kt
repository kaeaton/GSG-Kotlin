package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.version.VersionList
import kotlin.properties.Delegates

class GfeSearchState : TabState {

    /* What tab */
    override fun getTab(ctx: TabStateContext) = "GFE"

    /* Version */
    override fun getCurrentVersion(ctx: TabStateContext): String {
        return "version"
    }

    // setting up so the core can identify which tab the version
    // request is coming from.
    // I can't remember why I care.

    // Because you only need to pull it once.
    // Each tab can use the same version list.
    // I ended up putting the version initialization
    // and generation in TabStateContext because
    // it doesn't need to be reinitialized for each tab.
}
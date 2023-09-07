package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.version.VersionList
import kotlin.properties.Delegates

class NameSearchState: TabState {

    /* What tab */
    override fun getTab(ctx: TabStateContext) = "NAME"

    /* Version */
    override fun getCurrentVersion(ctx: TabStateContext): String {
        return "version"
    }
}
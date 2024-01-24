package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

class ComparisonState: TabState {

    /* What tab */
    override fun getTab(ctx: TabStateContext) = "COMP"

    /* Version */

    override var version: String by Delegates.observable(PrefsTabSearch.currentVersionHla)
    { _, _, newValue ->
        PrefsTabSearch.currentVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ ->  }
}
package org.b12x.gfe.core.controller.locistate

import org.b12x.gfe.core.controller.version.CreateNewHlaVersionObject
import org.b12x.gfe.core.controller.version.Version
import kotlin.properties.Delegates

class NameSearchState: LociState {

    /* What tab */
    override fun getTab(ctx: LociStateContext) = "NAME"

    /* Version */
    override var version: String by Delegates.observable(PrefsLociState.currentVersionHla)
    { _, _, newValue ->
        PrefsLociState.currentVersionHla = newValue
    }

    override var versionObject: Version by Delegates.observable(
        CreateNewHlaVersionObject.createVersionObject("HLA", version)
    ) { _, _, _ ->  }
}
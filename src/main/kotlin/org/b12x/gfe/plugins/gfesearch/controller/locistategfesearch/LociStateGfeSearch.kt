package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version
import tornadofx.View

interface LociStateGfeSearch {

    /* Version */
    var version: String
    var versionObject: Version
    fun updateVersions(ctx: LociStateContextGfeSearch)

    /* Locus*/
    var locus: String
    var locusEnum: LociEnum
    fun updateLocuses(ctx: LociStateContextGfeSearch)
    fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): View
}
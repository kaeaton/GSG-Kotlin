package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import tornadofx.View

interface LociStateGfeSearch {
    /* Loci */
//    var loci: String
    fun getLoci(ctx: LociStateContextGfeSearch): String

    /* Version */
    fun getCurrentVersion(ctx: LociStateContextGfeSearch): String
    fun setCurrentVersion(ctx: LociStateContextGfeSearch, currentVersion: String)
    fun updateVersions(ctx: LociStateContextGfeSearch)

    /* Locus*/
    fun getCurrentLocus(ctx: LociStateContextGfeSearch): LociEnum
    fun setCurrentLocus(ctx: LociStateContextGfeSearch, currentLocus: String)
    fun updateLocuses(ctx: LociStateContextGfeSearch)
    fun createNewSearchBoxes(ctx: LociStateContextGfeSearch): View
}
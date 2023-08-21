package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version

interface LociStateNameSearch {

    /* Version */
    var version: String
    var versionObject: Version
    fun updateVersions(ctx: LociStateContextNameSearch)

    /* Locus*/
    var locus: String
    var locusEnum: LociEnum
    fun updateLocuses(ctx: LociStateContextNameSearch)
}
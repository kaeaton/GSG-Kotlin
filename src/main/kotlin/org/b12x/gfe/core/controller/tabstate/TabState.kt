package org.b12x.gfe.core.controller.tabstate

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version

interface TabState {

    /* What Tab */
    fun getTab(ctx: TabStateContext): String

    /* Loci */
//    var loci: String

    /* Version */
    var version: String
    var versionObject: Version
//    fun getCurrentVersion(ctx: TabStateContext): String

    /* Locus*/
//    var locus: String
//    var locusEnum: LociEnum
//    fun updateLocuses(ctx: TabStateContext)

}
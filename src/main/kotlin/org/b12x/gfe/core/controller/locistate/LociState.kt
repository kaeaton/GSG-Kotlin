package org.b12x.gfe.core.controller.locistate

import org.b12x.gfe.core.controller.version.Version

interface LociState {

    /* What Tab */
    fun getTab(ctx: LociStateContext): String

    /* Loci */
//    var loci: String

    /* Version */
    var version: String
    var versionObject: Version
//    fun getCurrentVersion(ctx: LociStateContext): String

    /* Locus*/
//    var locus: String
//    var locusEnum: LociEnum
//    fun updateLocuses(ctx: LociStateContext)

}
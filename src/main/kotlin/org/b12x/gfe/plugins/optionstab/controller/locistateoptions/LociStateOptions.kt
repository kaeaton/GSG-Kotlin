package org.b12x.gfe.plugins.optionstab.controller.locistateoptions

import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxes

interface LociStateOptions {

    /* Version */
    var version: String
    var versionObject: Version
    fun updateVersions(ctx: LociStateContextOptions)

    /* Locus*/
    var locus: String
    var locusEnum: LociEnum
    fun updateLocuses(ctx: LociStateContextOptions)
}
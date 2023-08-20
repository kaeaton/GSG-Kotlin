package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.controller.version.CreateNewKirVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.plugins.namesearch.view.NameSearchMenuLocus
import org.b12x.gfe.plugins.namesearch.view.NameSearchChoiceBoxVersion
import tornadofx.*
import kotlin.properties.Delegates

class KirStateNameSearch : LociStateNameSearch {

    /* Version */

    override var version: String by Delegates.observable(
        PrefsNameSearch.currentNameSearchVersionKir
    ) { _, _, newValue -> PrefsNameSearch.currentNameSearchVersionKir = newValue }

    override var versionObject: Version by Delegates.observable(
        CreateNewKirVersionObject.createVersionObject()
    ) { _, _, _ ->  CreateNewKirVersionObject.createVersionObject()}

    override fun updateVersions(ctx: LociStateContextNameSearch) {
        val nameSearchChoiceBoxVersion = find(NameSearchChoiceBoxVersion::class)

        nameSearchChoiceBoxVersion.versionsList.clear()
        nameSearchChoiceBoxVersion.versionsList.add("2.7.0")

        nameSearchChoiceBoxVersion.currentVersion = version
    }

    /* Locus */

    override var locus: String by Delegates.observable(PrefsNameSearch.currentNameSearchLocusKir) { _, _, newValue ->
        PrefsNameSearch.currentNameSearchLocusKir = newValue
    }

    override var locusEnum: LociEnum by Delegates.observable(
        (KirLoci.values().find { it.fullName == locus }) as LociEnum
    ) { _, _, _ -> (KirLoci.values().find { it.fullName == locus }) as LociEnum }

    // I know it's not spelled locuses, but loci is already used.
    override fun updateLocuses(ctx: LociStateContextNameSearch) {
        val locusNames = versionObject.locusAvailable

        val nameSearchChoiceBoxLocus = find(NameSearchMenuLocus::class)
        val locObservableList = nameSearchChoiceBoxLocus.locusList
        locObservableList.clear()
        locObservableList.addAll(locusNames)

        nameSearchChoiceBoxLocus.currentLocus = locus
    }
}

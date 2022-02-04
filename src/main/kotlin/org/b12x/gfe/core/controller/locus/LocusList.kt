package org.b12x.gfe.core.controller.locus

import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.view.ChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import tornadofx.*

class LocusList(version: Version, choiceBoxLocus: ChoiceBoxLocus) {

    //    private var gfeSearchChoiceBoxLocus = find(choice::class)
//    private val stateContext = GfeSearchLayoutData.lociStateContextGfeSearch
//    private var currentLoci = stateContext.getLoci()

    var newLocusObservableList = choiceBoxLocus.locusObservableList
    var newLocusList = version.locusAvailable

    fun updateLocusList() {
        newLocusObservableList.clear()
        newLocusObservableList.addAll(newLocusList)
    }


}
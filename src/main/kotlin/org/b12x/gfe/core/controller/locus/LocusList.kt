package org.b12x.gfe.core.controller.locus

import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.view.ChoiceBoxLocus

class LocusList(version: Version, choiceBoxLocus: ChoiceBoxLocus) {

    var newLocusObservableList = choiceBoxLocus.locusObservableList
    var newLocusList = version.locusAvailable

    fun updateLocusList() {
        newLocusObservableList.clear()
        newLocusObservableList.addAll(newLocusList)
    }
}
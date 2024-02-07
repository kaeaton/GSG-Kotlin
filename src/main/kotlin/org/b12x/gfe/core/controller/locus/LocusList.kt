package org.b12x.gfe.core.controller.locus

import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.view.MenuLocus

class LocusList(version: Version, menuLocus: MenuLocus) {

    var newLocusObservableList = menuLocus.locusList
    var newLocusList = version.locusAvailable

    fun updateLocusList() {
        newLocusObservableList.clear()
        newLocusObservableList.addAll(newLocusList)
    }
}
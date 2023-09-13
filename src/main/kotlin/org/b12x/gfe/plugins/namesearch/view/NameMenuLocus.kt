package org.b12x.gfe.plugins.namesearch.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.core.view.MenuLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeViewMethods
import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*

class NameMenuLocus : View("GFE Search Locus Choice Box"), MenuLocus {

    val stateContext = LociStateContextNameSearch

    /* list of locuses */
    var locusNames : List<String> = stateContext.versionObject.locusAvailable
    override var locusList = observableListOf(locusNames)

    /* selected locus */
    val currentLocusProperty = SimpleStringProperty(stateContext.locus)
    override var currentLocus: String by currentLocusProperty

    // compare prior version to current version, if not a matching locus,
    // choiceBox.getSelectionModel().selectFirst(); selects the first option (Java)

    /* choiceBox */
    override var menuLocus = choicebox<String>(currentLocusProperty, locusList) {
        action {
            if (this.value != null) {
                stateContext.locus = (this.value.toString())
            }
            GfeViewMethods.resetArraysHard()
        }
    }

    override val root = hbox {
        add(menuLocus)
    }
}

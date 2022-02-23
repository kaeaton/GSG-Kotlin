package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.AvailableLoci
import org.b12x.gfe.core.view.ComboBoxLoci
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.PrefsGfeSearch
import tornadofx.*
import kotlin.properties.Delegates

object GfeSearchChoiceBoxLoci : View("Available Loci"), ComboBoxLoci {

    var stateContext = LociStateContextGfeSearch

    /* list of Loci */
    val loci: ObservableList<String> = observableListOf(AvailableLoci.AVAILABLE_LOCI)

    /* selected Loci */
    private var currentLociProperty = SimpleStringProperty(stateContext.loci)
    override var currentLoci: String by currentLociProperty
//    override var currentLoci: String by Delegates.observable(_, oldValue, newValue) {

//}

    /* choiceBox */
    override val choiceBoxLoci = choicebox<String>(currentLociProperty, loci) {
        action {

            stateContext.loci = this.value

            stateContext.updateVersions()
            stateContext.updateLocuses()
            GfeSearchLayoutData.resetArraysHard()
            find(GfeSearchChoiceBoxLocus::class).swapSearchBoxes(stateContext.getCurrentLocus())
        }
    }

    override val root = hbox {
        add(choiceBoxLoci)
    }
}

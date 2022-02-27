package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.core.controller.loci.AvailableLoci
import org.b12x.gfe.core.view.ComboBoxLoci
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

object GfeSearchChoiceBoxLoci : View(), ComboBoxLoci {

    private val stateContext = LociStateContextGfeSearch

    /* list of Loci */
    private val lociList = observableListOf(AvailableLoci.AVAILABLE_LOCI)

    /* selected Loci */
    private var currentLociProperty = SimpleStringProperty(stateContext.loci)
    override var currentLoci: String by currentLociProperty

    /* choiceBox */
    override val choiceBoxLoci = choicebox<String>(currentLociProperty, lociList) {
        action {
            stateContext.loci = this.value
            stateContext.updateVersions()

            stateContext.updateLocuses()
            GfeSearchLayoutData.resetArraysHard()
            find(GfeSearchChoiceBoxLocus::class).swapSearchBoxes(stateContext.locusEnum)
        }
    }

    override val root = hbox {
        add(choiceBoxLoci)
    }
}

package org.b12x.gfe.plugins.namesearch.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.AvailableLoci
import org.b12x.gfe.core.view.ComboBoxLoci
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch.LociStateContextNameSearch
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class NameSearchChoiceBoxLoci : View(), ComboBoxLoci {

    private val stateContext = LociStateContextNameSearch

    val lociListProperty = SimpleObjectProperty(observableListOf(AvailableLoci.AVAILABLE_LOCI))
    var lociList: ObservableList<String> by lociListProperty

    /* selected Loci */
    private var currentLociProperty = SimpleStringProperty(stateContext.loci)
    override var currentLoci: String by currentLociProperty

    /* choiceBox */
    override val choiceBoxLoci = choicebox<String>(currentLociProperty, lociList) {
        action {
            stateContext.loci = this.value
            stateContext.updateVersions()

            stateContext.updateLocuses()
            NameSearchLayoutData.resetArraysHard()
        }
    }

    override val root = hbox {
        add(choiceBoxLoci)
    }
}

package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.AvailableLoci
import org.b12x.gfe.core.view.ComboBoxLoci
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class GfeSearchChoiceBoxLoci : View(), ComboBoxLoci {

    private val stateContext = LociStateContextGfeSearch

//    val lociListProperty = SimpleObjectProperty(observableListOf(AvailableLoci.AVAILABLE_LOCI))
    val lociListProperty = SimpleObjectProperty(observableListOf(AvailableLoci.AVAILABLE_LOCI))
    var lociList: ObservableList<String> by lociListProperty
//    fun lociList() = getProperty(GfeSearchChoiceBoxLoci::lociListProperty)


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
            val gfeSearchChoiceBoxLocus = find(GfeSearchChoiceBoxLocus::class)
            gfeSearchChoiceBoxLocus.swapSearchBoxes(stateContext.locusEnum)
        }
    }

    override val root = hbox {
        add(choiceBoxLoci)
    }
}

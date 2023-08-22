package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.AvailableLoci
import org.b12x.gfe.core.view.MenuLoci
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class GfeMenuLoci : View(), MenuLoci {

    private val stateContext = LociStateContextGfeSearch

//    val lociListProperty = SimpleObjectProperty(observableListOf(AvailableLoci.AVAILABLE_LOCI))
    val lociListProperty = SimpleObjectProperty(observableListOf(AvailableLoci.AVAILABLE_LOCI))
    var lociList: ObservableList<String> by lociListProperty
//    fun lociList() = getProperty(GfeMenuLoci::lociListProperty)


    /* selected Loci */
    private var currentLociProperty = SimpleStringProperty(stateContext.loci)
    override var currentLoci: String by currentLociProperty

    /* choiceBox */
    override val menuLoci = choicebox<String>(currentLociProperty, lociList) {
        action {
            stateContext.loci = this.value
            stateContext.setState(this.value)
            stateContext.updateVersions()

            stateContext.updateLocuses()
            GfeViewData.resetArraysHard()
            find(GfeViewParent::class).swapSearchBoxes()
        }
    }

    override val root = hbox {
        add(menuLoci)
    }
}

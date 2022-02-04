package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.view.ChoiceBoxLocus
import tornadofx.*

class GfeSearchChoiceBoxLocus : View("GFE Search Locus Choice Box"), ChoiceBoxLocus {

    private val stateContext = GfeSearchLayoutData.lociStateContextGfeSearch

    var locusNames : List<String> = GfeSearchLayoutData.currentLocusList
//    var locusNames : List<String> = listOf("one", "Two")
    override var locusObservableList: ObservableList<String> = FXCollections.observableArrayList(locusNames)

    private val currentLocusProperty = SimpleStringProperty(GfeSearchLayoutData.currentLocus)
    override var currentLocus: String by currentLocusProperty

    override var choiceBoxLocus = choicebox<String>(currentLocusProperty, locusObservableList) {
        action {
            if (this.value != null) {
                GfeSearchLayoutData.updateLocus(this.value)
//                stateContext.setCurrentLocus(this.value)
//                currentLocus = this.value
            }
            GfeSearchLayoutData.resetArraysHard()
//            swapSearchBoxes(stateContext.getCurrentLocus())
            swapSearchBoxes(GfeSearchLayoutData.currentLocusEnum)
        }
    }

    override val root = hbox {
        add(choiceBoxLocus)
    }

    // swaps the old set of search boxes out, and puts in the new set
    // based on the locus passed to it.
    fun swapSearchBoxes(loci: LociEnum) {
        find(GfeSearchViewParent::class).gfeSearchBoxes.removeFromParent()
        find(GfeSearchViewParent::class).gfeSearchBoxes = stateContext.createNewSearchBoxes()
        find(GfeSearchViewParent::class).root.center.add(find(GfeSearchViewParent::class).gfeSearchBoxes)
    }
}

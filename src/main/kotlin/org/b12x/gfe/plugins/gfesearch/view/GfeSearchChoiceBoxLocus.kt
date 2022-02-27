package org.b12x.gfe.plugins.gfesearch.view

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.view.ChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeSearchChoiceBoxLocus : View("GFE Search Locus Choice Box"), ChoiceBoxLocus {

    private val stateContext = LociStateContextGfeSearch

    /* list of locuses */
    var locusNames : List<String> = stateContext.versionObject.locusAvailable
//    var locusNames : List<String> = listOf("one", "Two")
    override var locusList = observableListOf(locusNames)

    /* selected locus */
    private val currentLocusProperty = SimpleStringProperty(stateContext.locus)
    override var currentLocus: String by currentLocusProperty

    /* choiceBox */
    override var choiceBoxLocus = choicebox<String>(currentLocusProperty, locusList) {
        action {
            if (this.value != null) {
                stateContext.locus = (this.value.toString())
            }
            GfeSearchLayoutData.resetArraysHard()
            swapSearchBoxes(stateContext.locusEnum)
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

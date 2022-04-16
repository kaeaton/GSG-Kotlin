package org.b12x.gfe.plugins.gfesearch.view

import io.ktor.util.reflect.*
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.view.ChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchBoxesContainer
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxes
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesHla
import org.b12x.gfe.plugins.gfesearch.view.searchboxes.GfeSearchViewSearchBoxesKir
import tornadofx.*
import kotlin.reflect.full.memberExtensionFunctions

class GfeSearchChoiceBoxLocus : View("GFE Search Locus Choice Box"), ChoiceBoxLocus {

    val stateContext = LociStateContextGfeSearch

    /* list of locuses */
    var locusNames : List<String> = stateContext.versionObject.locusAvailable
    override var locusList = observableListOf(locusNames)

    /* selected locus */
    val currentLocusProperty = SimpleStringProperty(stateContext.locus)
    override var currentLocus: String by currentLocusProperty

    // compare prior version to current version, if not a matching locus,
    // choiceBox.getSelectionModel().selectFirst(); selects the first option (Java)

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
    fun swapSearchBoxes(locus: LociEnum) {
        val gfeSearchBoxesContainer = find(GfeSearchBoxesContainer::class)
        println(gfeSearchBoxesContainer.toString())
        val searchBoxes = gfeSearchBoxesContainer.gfeSearchBoxes
        println(searchBoxes)
        val test = gfeSearchBoxesContainer::gfeSearchBoxes
        println(test.toString())

//        find(GfeSearchViewSearchBoxes::class).root.removeFromParent()//:class.objectInstance).removeFromParent()
//        find(GfeSearchViewSearchBoxesHla::class).root.removeFromParent()//:class.objectInstance).removeFromParent()
        find(GfeSearchBoxesContainer::class).add(stateContext.createNewSearchBoxes().root)
//        find(GfeSearchBoxesContainer::class).gfeSearchBoxes = stateContext.createNewSearchBoxes()
//        find(GfeSearchBoxesContainer::class).root.center.add(find(GfeSearchViewParent::class).gfeSearchBoxes)


//        searchBoxes.removeFromParent()
//        val gfeSearchBoxes = stateContext.createNewSearchBoxes()
//        find(GfeSearchBoxesContainer::class).root.add(stateContext.createNewSearchBoxes())
//        GfeSearchBoxesContainer.root.add(gfeSearchBoxes)
//        GfeSearchViewParent.root.center.add(gfeSearchBoxes)

//        find(GfeSearchViewParent::class).gfeSearchBoxesContainer.removeFromParent()
//        find(GfeSearchViewParent::class).gfeSearchBoxes = stateContext.createNewSearchBoxes()
//        find(GfeSearchViewParent::class).root.center.add(find(GfeSearchViewParent::class).gfeSearchBoxes)
    }
}

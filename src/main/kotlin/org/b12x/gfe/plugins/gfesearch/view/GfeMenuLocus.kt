package org.b12x.gfe.plugins.gfesearch.view

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import org.b12x.gfe.core.controller.loci.HlaLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.core.view.MenuLocus
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import tornadofx.*

class GfeMenuLocus : View("GFE Search Locus Choice Box"), MenuLocus {

    val stateContext = LociStateContextGfeSearch

    /* list of locuses */
    var locusNames: List<String> = stateContext.versionObject.locusAvailable
    override var locusList: ObservableList<String> = observableListOf(locusNames)

    /* selected locus */
    val currentLocusProperty = SimpleStringProperty(stateContext.locus)
    override var currentLocus: String by currentLocusProperty

    // attempt at KotlinRX observable must be a list. I don't think this is the way to go.
    val observable: Observable<String> = listOf(currentLocus).toObservable()


    // compare prior version to current version, if not a matching locus,
//     choiceBox.getSelectionModel().selectFirst(); // selects the first option (Java)

    /* choiceBox */
    override var menuLocus = choicebox<String>(currentLocusProperty, locusList) {
        action {
            if (this.value != null) {
                stateContext.locus = (this.value.toString())
                stateContext.locusEnum = (HlaLoci.values().find { it.fullName == this.value.toString() }) as LociEnum
            }
            GfeViewMethods.resetArraysHard()
            find(GfeViewParent::class).swapSearchBoxes()
        }
    }

    override val root = hbox {
        add(menuLocus)
    }
}
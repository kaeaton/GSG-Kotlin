package org.b12x.gfe.core.view

import javafx.beans.property.SimpleStringProperty
import org.b12x.gfe.utilities.locus.HlaLoci
import org.b12x.gfe.plugins.gfesearch.view.GfeLayoutData
import org.b12x.gfe.plugins.gfesearch.view.GfeLayoutData.Companion.selectedLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchView
import org.b12x.gfe.utilities.preference.PrefsOld
import tornadofx.*
import kotlin.collections.ArrayList

class LocusComboBox : View("My View") {

    //    private val lociOptions = FXCollections.observableArrayList("HLA", "KIR")
    val prefs = PrefsOld()

    private val lociNames = ArrayList<String>()

    init {
        HlaLoci.values().forEach { lociNames.add(it.toString()) }
    }

    //    private val currentLocusProperty = SimpleStringProperty()
    val currentLocus = SimpleStringProperty().get()
//    var currentLocus by currentLocusProperty
//    currentLocus = "HLA-A"

    var foo by property(prefs.currentLocus)
    fun fooProperty() = getProperty(LocusComboBox::foo)

//    var foo by property("baz")
//    fun fooProperty() = getProperty(Bar::foo)

    override val root = vbox {
        choicebox<String>(fooProperty(), lociNames) {
            action {
                selectedLocus = HlaLoci.values().find { it.fullName == this.value } ?: HlaLoci.A
                GfeLayoutData.resetArraysHard()
                find(GfeSearchView::class).swapSearchBoxes(selectedLocus!!)
            }
        }
    }
}

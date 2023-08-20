package org.b12x.gfe.core.view

import javafx.scene.control.ChoiceBox

//import org.b12x.gfe.utilities.Loci

interface MenuLoci {
    var currentLoci: String
    val menuLoci: ChoiceBox<String>
}
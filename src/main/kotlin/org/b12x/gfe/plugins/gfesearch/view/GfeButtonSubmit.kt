package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

object GfeButtonSubmit : View("Submit") {



    override val root = vbox {
        button("Submit") {
            action {
                GfeViewMethods.submitData()
//                GfeViewMethods.resetArraysSoft()
            }
        }
    }
}


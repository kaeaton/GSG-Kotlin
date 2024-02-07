package org.b12x.gfe.plugins.gfesearch.view

import org.b12x.gfe.plugins.gfesearch.model.output.files.GfeOutputCsv
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


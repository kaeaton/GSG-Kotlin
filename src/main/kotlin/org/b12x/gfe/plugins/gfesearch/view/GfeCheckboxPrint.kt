package org.b12x.gfe.plugins.gfesearch.view

import tornadofx.*

class GfeCheckboxPrint : View() {
    val printCheckBox = checkbox("Print to file") {
        style { padding = box(0.px, 0.px, 0.px, 0.px) }
//        action {
//            if (isSelected) {
//                GfeViewMethods.checkList.forEach { it.isSelected = true }
//            } else {
//                GfeViewMethods.checkList.forEach { it.isSelected = false }
//            }
//        }
    }

    override val root = hbox {
        add(printCheckBox)
    }
}
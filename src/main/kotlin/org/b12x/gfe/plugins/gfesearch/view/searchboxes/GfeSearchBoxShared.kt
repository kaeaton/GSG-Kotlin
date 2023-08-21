package org.b12x.gfe.plugins.gfesearch.view.searchboxes
import javafx.geometry.Pos
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData
import tornadofx.*

class GfeSearchBoxShared() : Fragment(){

    val stateContext = LociStateContextGfeSearch

    val selectAllCheckBox = checkbox {
        style { padding = box(10.px, 10.px, 0.px, 10.px) }
        action {
            if (isSelected) {
                GfeViewData.checkList.forEach { it.isSelected = true }
            } else {
                GfeViewData.checkList.forEach { it.isSelected = false }
            }
        }
    }

    override val root = vbox {
        val selectAllBox = vbox {
            style {
                prefWidth = 80.px
                alignment = Pos.CENTER
            }
        }
        selectAllBox.add(selectAllCheckBox)
        selectAllBox.add(label(stateContext.locus) {
            style {
                padding = box(15.px, 0.px)
                fontSize = 15.px
            }
        })
    }
}
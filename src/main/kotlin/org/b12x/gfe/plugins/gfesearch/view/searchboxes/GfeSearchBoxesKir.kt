package org.b12x.gfe.plugins.gfesearch.view.searchboxes

import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.GfeViewMethods
import tornadofx.*

class GfeSearchBoxesKir(loci: LociEnum) : Fragment("KIR GFE Search Boxes"), GfeSearchBoxes {

    private val stateContext = LociStateContextGfeSearch

    val gfeSearchBoxShared = GfeSearchBoxShared()
    val selectAllCheckBox: CheckBox = gfeSearchBoxShared.selectAllCheckBox

    val currentKirLocus = stateContext.locusEnum as KirLoci
    val completedSearchBox = completedSearchBoxGenerator(currentKirLocus.exons)

    override val root = vbox {
        label {
            text = "Check all"
            style {
                padding = box(0.px, 0.px, 0.px, 10.px)
                fontSize = 13.px
            }
        }
        add(Group(completedSearchBox))
        style {
            padding = box(25.px, 0.px)
        }
    }

    override fun completedSearchBoxGenerator(numberOfBoxes: Int): HBox {
        val completedSearchBox = hbox {
            style {
                padding = box(0.px, 0.px, 0.px, 0.px)
            }
        }
        completedSearchBox.add(Group(gfeSearchBoxShared.root))

        completedSearchBox.add(Group(individualSearchBoxAssembler("Workshop Status")))
        completedSearchBox.add(Group(individualSearchBoxAssembler("5' UTR")))

        for (i in 1 until numberOfBoxes) {
            if (currentKirLocus.skippedExons.any{it == i} ) {
                completedSearchBox.add(Group(individualSearchBoxAssembler("Exon $i", true)))
                completedSearchBox.add(Group(individualSearchBoxAssembler("Intron $i", true)))
                continue
            }
            completedSearchBox.add(Group(individualSearchBoxAssembler("Exon $i")))
            completedSearchBox.add(Group(individualSearchBoxAssembler("Intron $i")))
        }

        completedSearchBox.add(Group(individualSearchBoxAssembler("Exon $numberOfBoxes")))
        completedSearchBox.add(Group(individualSearchBoxAssembler("3' UTR")))

        return completedSearchBox
    }


    private fun individualSearchBoxAssembler(labelName: String, skipped: Boolean = false): VBox {
        var currentCheckBox: CheckBox by singleAssign()
        var currentTextField: TextField by singleAssign()

        val rotatedLabel = label(labelName) {
            style {
                rotate = 90.deg
                padding = box(0.px, 0.px, 0.px, 10.px)
                fontSize = Dimension(1.3, Dimension.LinearUnits.em)
            }
        }

        val searchBoxComponent = vbox {
            currentCheckBox = checkbox {
                style {
                    padding = box(10.px)
                }
            }
            currentCheckBox.selectedProperty().addListener { _, _, _ ->
                if(!currentCheckBox.isSelected) {
                    selectAllCheckBox.isSelected = false
                }
                setOnKeyPressed { if (it.code == KeyCode.ENTER) GfeViewMethods.submitData() }
            }

            currentTextField = textfield("") {
                filterInput { it.controlNewText.isInt() }
                style {
                    prefWidth = 40.px
                    prefHeight = 25.px
                    padding = box(0.px, 5.px, 0.px, 5.px)
                    alignment = Pos.CENTER
                }
                action {
                    GfeViewMethods.submitData()
                }
            }
//            currentTextField.textProperty()
//                .addListener { observable, oldValue, newValue ->
//                    println("textfield changed from $oldValue to $newValue")
//                }

            if (labelName == "Workshop Status") {
                currentTextField.filterInput { it.controlNewText.any() }
                currentTextField.setText("w")
            }

            style {
                maxWidth = 60.px
                alignment = Pos.CENTER
                padding = box(0.px, 5.px, 0.px, 5.px)
                isDisable = skipped
            }
        }
        searchBoxComponent.add(Group(rotatedLabel))

        GfeViewMethods.checkList.add(currentCheckBox)
        GfeViewMethods.textList.add(currentTextField)

        return searchBoxComponent
    }
}

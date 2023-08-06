package org.b12x.gfe.plugins.gfesearch.view.searchboxes

import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.b12x.gfe.core.controller.loci.KirLoci
import org.b12x.gfe.core.controller.loci.LociEnum
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutDataModel
import tornadofx.*

class GfeSearchViewSearchBoxesKir(loci: LociEnum) : View("KIR GFE Search Boxes"), GfeSearchViewSearchBoxes {

    private val stateContext = LociStateContextGfeSearch

    val gfeSearchBoxShared = GfeSearchBoxShared()
    val selectAllCheckBox: CheckBox = gfeSearchBoxShared.selectAllCheckBox

    val currentKirLocus = stateContext.locusEnum as KirLoci
    val completedSearchBox = completedSearchBoxGenerator(currentKirLocus.exons)

    val model = GfeSearchLayoutDataModel()
    val newCheckBoxList = observableListOf<CheckBox>()
    val newTextFieldList = observableListOf<TextField>()
    lateinit var newSearchBoxes: View

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

        model.checkList.value = newCheckBoxList
        model.textList.value = newTextFieldList
        model.searchBoxes.value = completedSearchBox

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
            currentCheckBox.selectedProperty().addListener { observable, oldValue, newValue ->
                if(!currentCheckBox.isSelected) {
                    selectAllCheckBox.isSelected = false
                }
            }

            currentTextField = textfield("") {
                filterInput { it.controlNewText.isInt() }
                style {
                    prefWidth = 40.px
                    prefHeight = 25.px
                    padding = box(0.px, 5.px, 0.px, 5.px)
                    alignment = Pos.CENTER
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

        newCheckBoxList.add(currentCheckBox)
        newTextFieldList.add(currentTextField)

        return searchBoxComponent
    }
}

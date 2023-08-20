package org.b12x.gfe.core.controller.locus

import javafx.embed.swing.JFXPanel

class LocusListTest {

    // starts JavaFX so the rest of the tests can use it
    val jFXPanel = JFXPanel()

//    val testChoiceBoxLocus = find(TestChoiceBoxLocus::class) // choiceBox items = 2
//
//    val goalTestList = listOf("HLA, KIR, ABO") // after choiceBox items will hopefully = 3
//    val testVersionObject = Version(folder = File(""), name = "Test", locusAvailable = goalTestList)
//
//    @Test
//    fun testUpdateLocusList_numOfLocus() {
//        val locusList = LocusList(testVersionObject, testChoiceBoxLocus)
//        locusList.updateLocusList()
//        assertEquals(goalTestList.size, testChoiceBoxLocus.choiceBoxLocus.items.size)
//    }

    companion object {
//        class TestChoiceBoxLocus : View(), MenuLocus {
//            override var locusObservableList: ObservableList<String> = observableListOf("HLA", "KIR")
//            val currentLocusProperty = SimpleStringProperty("HLA")
//            override var currentLocus: String by currentLocusProperty
//            override var choiceBoxLocus = choicebox<String>(currentLocusProperty, locusObservableList) {}
//
//            override val root = hbox {
//                add(choiceBoxLocus)
//            }
//        }
    }
}

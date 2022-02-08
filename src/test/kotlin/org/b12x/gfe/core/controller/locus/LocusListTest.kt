package org.b12x.gfe.core.controller.locus

import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.embed.swing.JFXPanel
import javafx.scene.Parent
import javafx.scene.control.ChoiceBox
import javafx.stage.Stage
import org.b12x.gfe.core.controller.version.LocalVersions
import org.b12x.gfe.core.controller.version.Version
import org.b12x.gfe.core.view.ChoiceBoxLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.gfesearch.view.TestApp
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import tornadofx.*
import java.io.File
import kotlin.system.exitProcess

class LocusListTest {

    val jFXPanel = JFXPanel()

    val testChoiceBoxLocus = find(TestChoiceBoxLocus::class)

    val goalTestList = listOf("HLA, KIR, ABO")
    val testVersionObject = Version(folder = File(""), name = "Test", locusAvailable = goalTestList)

    @Test
    fun testUpdateLocusList_numOfLocus() {
        val locusList = LocusList(testVersionObject, testChoiceBoxLocus)
        locusList.updateLocusList()
        assertEquals(goalTestList.size, testChoiceBoxLocus.choiceBoxLocus.items.size)
    }

    companion object {
        class TestChoiceBoxLocus : View(), ChoiceBoxLocus {
            override var locusObservableList: ObservableList<String> = observableListOf("HLA", "KIR")
            val currentLocusProperty = SimpleStringProperty("HLA")
            override var currentLocus: String by currentLocusProperty
            override var choiceBoxLocus = choicebox<String>(currentLocusProperty, locusObservableList) {}

            override val root = hbox {
                add(choiceBoxLocus)
            }
        }
    }
}

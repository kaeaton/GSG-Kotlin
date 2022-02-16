package org.b12x.gfe.plugins.gfesearch.controller

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.checkList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.currentLoci
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.currentLocus
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.currentVersion
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.textFormat
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.textList
import org.b12x.gfe.plugins.gfesearch.view.GfeSearchLayoutData.writeToFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CreateNewGfeSearchDataTest {
    // necessary to initialize JavaFX
    val jfxPanel = JFXPanel()

    @Test
    fun createDataFile_validGfeSearchDataObject() {
        Mockito.mockStatic(GfeSearchLayoutData::class.java).use { mocked ->
            mocked.verify {
                currentLoci = "KIR"
                currentVersion = "2.0.1"
                currentLocus = "HLA-DQB1"
                textFormat = "TSV"
                writeToFile = true
                checkList = testCheckList
                textList = testTextList
            }
        }

        val regexString = "^HLA-DQB1w-(\\d+)-([1-9]{1}|\\d{2,6})-3-7-2$"
        val headerString = "HLA-DQB1w-*-x-3-7-2"

        val gfeSearchData = CreateNewGfeSearchData.generateSearchData()

        assertEquals("KIR", gfeSearchData.loci)
        assertEquals("2.0.1", gfeSearchData.version)
        assertEquals("HLA-DQB1", gfeSearchData.locus)
        assertEquals(testCheckList, gfeSearchData.checkBoxList)
        assertEquals(testTextList, gfeSearchData.textFieldList)
        assertEquals(regexString, gfeSearchData.regex)
        assertEquals(headerString, gfeSearchData.header)
        assertEquals("TSV", gfeSearchData.textFormat)
        assertTrue(gfeSearchData.writeToFile)
    }

    companion object {
        // necessary to initialize JavaFX
        val jfxPanel = JFXPanel()

        val cb1 = CheckBox()
        val cb2 = CheckBox()
        val cb3 = CheckBox()
        val cb4 = CheckBox()
        val cb5 = CheckBox()
        val cb6 = CheckBox()

        val tf1 = TextField("w")
        val tf2 = TextField()
        val tf3 = TextField()
        val tf4 = TextField("3")
        val tf5 = TextField("7")
        val tf6 = TextField("2")

        fun createCBList(): MutableList<CheckBox> {
            cb3.isSelected = true
            cb4.isSelected = true
            return mutableListOf(cb1, cb2, cb3, cb4, cb5, cb6)
        }

        val testCheckList = createCBList()
        val testTextList = mutableListOf(tf1, tf2, tf3, tf4, tf5, tf6)
    }
}
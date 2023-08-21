package org.b12x.gfe.plugins.gfesearch.controller

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData.checkList
//import org.b12x.gfe.plugins.gfesearch.view.GfeViewData.currentLoci
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData.textFormat
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData.textList
import org.b12x.gfe.plugins.gfesearch.view.GfeViewData.writeToFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.io.File

class CreateNewGfeSearchDataTest {
    // necessary to initialize JavaFX
    val jfxPanel = JFXPanel()

    @Test
    fun createDataFile_validGfeSearchDataObject() {
        Mockito.mockStatic(GfeViewData::class.java).use { mocked ->
            mocked.verify {
//                currentLoci = "KIR"
//                currentVersion = "2.7.0"
//                currentLocus = "KIR2DL4"
                textFormat = "TSV"
                writeToFile = true
                checkList = testCheckList
                textList = testTextList
            }
        }

        val regexString = "^KIR2DL4w-(\\d+)-([1-9]{1}|\\d{2,6})-3-7-2$"
        val headerString = "KIR2DL4w-*-x-3-7-2"
        val userDirectory = System.getProperty("user.home")

        val gfeSearchData = CreateNewGfeSearchData.generateSearchData()

        assertEquals("KIR", gfeSearchData.loci)
//        assertEquals("2.7.0", gfeSearchData.version)
        assertEquals("KIR2DL4", gfeSearchData.locus)
        assertEquals(testCheckList, gfeSearchData.checkBoxList)
        assertEquals(testTextList, gfeSearchData.textFieldList)
        assertEquals(regexString, gfeSearchData.regex)
        assertEquals(headerString, gfeSearchData.header)
        assertEquals("TSV", gfeSearchData.textFormat)
        assertTrue(gfeSearchData.writeToFile)
        assertEquals(File("${userDirectory}/Documents/GSG/GSGData/KIR/2.7.0/neo4j_KIR_2.7.0_Download.csv"), gfeSearchData.dataFile)
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
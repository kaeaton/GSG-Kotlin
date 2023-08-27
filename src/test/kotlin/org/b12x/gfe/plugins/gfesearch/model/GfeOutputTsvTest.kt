package org.b12x.gfe.plugins.gfesearch.model

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.GSG
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.core.model.GetDataFiles
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.controller.searchdata.GfeSearchData
import org.b12x.gfe.plugins.gfesearch.model.output.files.GfeOutputTsv
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildHeaderString
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildRegexString
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import tornadofx.*
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class GfeOutputTsvTest {
    private val stateContext = LociStateContextGfeSearch


    val gfeOutputTsv = GfeOutputTsv(finalGfeSearchData)

//    @BeforeAll
//    @TestInstance(launch<GSG>())
//    fun startJavaFx() {
//        launch<GSG>()
//    }

//    @AfterAll
//    fun killJavaFx() {
//        exitProcess(0)
//    }
    @Test
    fun buildHeaderLine1_true() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        val expected = "File generated at: $current"
        assertEquals(expected, gfeOutputTsv.headerLine1)
    }

    @Test
    fun buildHeaderLine2_true() {
        val expected = "Data source: https://dev-gfedb.b12x.org:7473/browser/ - HLA-DRB1"
        assertEquals(expected, gfeOutputTsv.headerLine2)
    }

    @Test
    fun buildHeaderLine3_true() {
        val expected = "Data source: https://dev-gfedb.b12x.org:7473/browser/ - HLA-DRB1"
        assertEquals(expected, gfeOutputTsv.headerLine2)
    }





//    @AfterAll
//    fun resetPrefs() {
//        /* Prefs Reset */
//        PrefsCore.nuclearOption()
//    }

    companion object {

        // necessary to initialize JavaFX
        val jfxPanel = JFXPanel()

        val cb1 = CheckBox()
        private val cb2 = CheckBox()
        private val cb3 = CheckBox()
        private val cb4 = CheckBox()
        private val cb5 = CheckBox()
        private val cb6 = CheckBox()

        private val tf1 = TextField("w")
        private val tf2 = TextField()
        private val tf3 = TextField()
        private val tf4 = TextField("3")
        private val tf5 = TextField("7")
        private val tf6 = TextField("2")

        fun createCBList(): MutableList<CheckBox> {
            cb3.isSelected = true
            cb4.isSelected = true
            return mutableListOf(cb1, cb2, cb3, cb4, cb5, cb6)
        }

        // Regex:

        val testCheckList = createCBList()
        val testTextList = mutableListOf(tf1, tf2, tf3, tf4, tf5, tf6)

        val gfeSearchData = GfeSearchData(
            loci = "HLA",
            version = "3.31.0",
            locus = "HLA-DRB1",
            checkBoxList = testCheckList,
            textFieldList = testTextList,
            regex = "".toRegex(),
            header = "",
            textFormat = "TSV",
            writeToFile = true,
            dataFile = File(
                GetDataFiles.retrieveDataFiles(
                    loci = "HLA",
                    version = "3.31.0",
                    locus = "HLA-DRB1"
                )
            )
        )

        fun gfeSearchDataComplete(searchData: GfeSearchData): GfeSearchData {
            BuildRegexString.assembleRegexString(searchData)
            println("TestData regex: ${searchData.regex}")
            BuildHeaderString.assembleHeaderString(searchData)
            println("TestData header: ${searchData.header}")
            return searchData
        }
        val finalGfeSearchData = gfeSearchDataComplete(gfeSearchData)
    }
}
package org.b12x.gfe.plugins.gfesearch.controller

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

class GfeSearchDataTest {
    // necessary to initialize JavaFX
    val jfxPanel = JFXPanel()

    val gfeSearchData = GfeSearchData(
        checkBoxList =  mutableListOf(CheckBox(), CheckBox(), CheckBox(), CheckBox()),
        textFieldList = mutableListOf(TextField(), TextField(), TextField(), TextField()),
        dataFile = File("")
    )

    @Test
    fun getDefaultGfeData() {
        assertEquals("", gfeSearchData.regex)
        assertEquals("CSV", gfeSearchData.textFormat)
        assertEquals(false, gfeSearchData.writeToFile)
        assertEquals(gfeSearchData.checkBoxList.size, gfeSearchData.textFieldList.size)
    }
}
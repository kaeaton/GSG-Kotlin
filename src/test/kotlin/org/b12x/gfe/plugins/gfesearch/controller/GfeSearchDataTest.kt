package org.b12x.gfe.plugins.gfesearch.controller

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class GfeSearchDataTest {
    val emptyGfeSearchData = GfeSearchData()
//    val testApp = TestApp()

    @Test
    fun getDefaultGfeData() {
        assertEquals(ArrayList<CheckBox>(), emptyGfeSearchData.checkArray)
        assertEquals(ArrayList<TextField>(), emptyGfeSearchData.textArray)
        assertEquals("", emptyGfeSearchData.regex)
        assertEquals("CSV", emptyGfeSearchData.textFormat)
        assertEquals(false, emptyGfeSearchData.writeToFile)
        assertTrue(emptyGfeSearchData.checkArray.isEmpty())
        assertTrue(emptyGfeSearchData.textArray.isEmpty())
    }

//    @Test
//    fun testTestApp() {
//        assertEquals()
//    }

}
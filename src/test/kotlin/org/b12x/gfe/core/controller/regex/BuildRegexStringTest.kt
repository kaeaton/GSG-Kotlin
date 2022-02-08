package org.b12x.gfe.core.controller.regex

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class BuildRegexStringTest {
    val jfxPanel = JFXPanel()
    val buildRegexString = BuildRegexString()

    @Test
    fun numberProvided_returnString() {
        val textFields = listOf("3", "4")
        assertEquals("3-", buildRegexString.numberProvided(textFields[0].toInt()))
        assertEquals("4-", buildRegexString.numberProvided(textFields[1].toInt()))
    }

    @Test
    fun checkBoxChecked_returnRegexString() {
        val checkBoxes = listOf(CheckBox(), CheckBox())
        checkBoxes[0].isSelected = true
        checkBoxes[1].isSelected = false
        assertEquals("([1-9]{1}|\\\\d{2,6})-", buildRegexString.checkBoxChecked(checkBoxes[0]))
        assertEquals("", buildRegexString.checkBoxChecked(checkBoxes[1]))
    }
}
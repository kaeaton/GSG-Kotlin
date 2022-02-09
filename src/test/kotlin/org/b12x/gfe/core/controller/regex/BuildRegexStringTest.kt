package org.b12x.gfe.core.controller.regex

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class BuildRegexStringTest {
    val jfxPanel = JFXPanel()
    val buildRegexString = BuildRegexString()

    @Test
    fun numberProvided_returnRegexString() {
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
        assertEquals("(\\\\d+)-", buildRegexString.checkBoxChecked(checkBoxes[1]))
    }

    @Test
    fun noCheckNorNumber_returnRegexString() {
        assertEquals("(\\\\d+)-", buildRegexString.noCheckNorNumber())
    }

    @ParameterizedTest
    @ValueSource(strings = ["w", "z", " ", "", "11"])
    fun workshopStatus_returnW(textFieldContents: String) {
        assertEquals("w", buildRegexString.workshopStatus(textFieldContents))
    }

    @ParameterizedTest
    @ValueSource(strings = ["\\d", "\\d-", "\\d-$", "([1-9]{1}|\\d{2,6})-", "234-$", "\\d$", "234$",  "([1-9]{1}|\\d{2,6})$"])
    fun CloseRegex_removeDashThenAddDollarSign(stringInput: String) {
        val testingRegex = """^.+[^-]\$""".toRegex()
        val parsedStringInput = buildRegexString.closeRegex(stringInput)
        val parsedRegex = testingRegex.matches(parsedStringInput)
        assertTrue(parsedRegex)
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "", "^", "$", "-"])
    fun CloseRegex_throwsIllegalArgumentException(stringInput: String) {
        val testingRegex = """^.+[^-]\$""".toRegex()
        val parsedStringInput = buildRegexString.closeRegex(stringInput)
        assertThrows(IllegalArgumentException::class.java) {
            testingRegex.matches(parsedStringInput)
        }
    }
}
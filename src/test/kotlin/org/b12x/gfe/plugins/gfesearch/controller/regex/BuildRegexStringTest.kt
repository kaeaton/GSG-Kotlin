package org.b12x.gfe.plugins.gfesearch.controller.regex

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.controller.GfeSearchData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class BuildRegexStringTest {
    val jfxPanel = JFXPanel()

    @Test
    fun numberProvided_returnRegexString() {
        val textFields = listOf(TextField("3"), TextField("4"))
        assertEquals("3-", BuildRegexString.numberProvided(textFields[0]))
        assertEquals("4-", BuildRegexString.numberProvided(textFields[1]))
    }

    @Test
    fun checkBoxChecked_returnRegexString() {
        val checkBoxes = listOf(CheckBox(), CheckBox())
        checkBoxes[0].isSelected = true
        checkBoxes[1].isSelected = false
        assertEquals("([1-9]{1}|\\d{2,6})-", BuildRegexString.checkBoxChecked(checkBoxes[0]))
        assertEquals("(\\d+)-", BuildRegexString.checkBoxChecked(checkBoxes[1]))
    }

    @Test
    fun workshopStatus_returnW() {
        val textField = TextField("fes")
        assertEquals("w-", BuildRegexString.workshopStatus(textField))
    }

    @ParameterizedTest
    @ValueSource(strings = ["\\d", "\\d-", "\\d-$", "([1-9]{1}|\\d{2,6})-", "234-$", "\\d$", "234$", "([1-9]{1}|\\d{2,6})$"])
    fun closeRegex_removeDashThenAddDollarSign_true(stringInput: String) {
        val testingRegex = """^.*[^-]\$""".toRegex()
        val parsedStringInput = BuildRegexString.closeRegex(stringInput)
        val parsedRegex = testingRegex.matches(parsedStringInput)
        assertTrue(parsedRegex)
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "", "^", "$", "-"])
    fun closeRegex_throwsIllegalArgumentException(stringInput: String) {
        assertThrows<IllegalArgumentException> {
            val testingRegex = """^.+[^-]\$""".toRegex()
            val parsedStringInput = BuildRegexString.closeRegex(stringInput)
            testingRegex.matches(parsedStringInput)
        }
    }

    @Test
    fun doTheListsMatch_true() {
        assertTrue(BuildRegexString.doTheListsMatch(testGfeSearchData.checkBoxList, testGfeSearchData.textFieldList))
    }

    @Test
    fun assembleRegexString_throwsIllegalArgumentException_listLengthNotSame() {
        val shortCheckBoxList = listOf(CheckBox(), CheckBox(), CheckBox())
        val shortTextFieldList = listOf(TextField(), TextField())
        val exceptionGfeSearchData = GfeSearchData(checkBoxList = shortCheckBoxList, textFieldList = shortTextFieldList)
        assertThrows<IllegalArgumentException> {
            BuildRegexString.assembleRegexString(exceptionGfeSearchData)
        }
    }

    @Test
    fun assembleRegexString_returnValidString() {
        val desiredRegexString = "^HLA-Aw-(\\d+)-([1-9]{1}|\\d{2,6})-3-7-2$"
        BuildRegexString.assembleRegexString(testGfeSearchData)
        assertEquals(desiredRegexString, testGfeSearchData.regex)
    }

    companion object {
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

        fun createCBList(): List<CheckBox> {
            cb3.isSelected = true
            cb4.isSelected = true
            return listOf(cb1, cb2, cb3, cb4, cb5, cb6)
        }

        val testGfeSearchData = GfeSearchData(
            checkBoxList = createCBList(),
            textFieldList = listOf(tf1, tf2, tf3, tf4, tf5, tf6)
        )
    }
}

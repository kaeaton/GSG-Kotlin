package org.b12x.gfe.plugins.gfesearch.controller.regex

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.controller.GfeSearchData
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class BuildHeaderStringTest {
    // necessary to initialize JavaFX
    val jfxPanel = JFXPanel()

    @Test
    fun numberProvided_returnHeaderString() {
        val textFields = listOf(TextField("3"), TextField("4"))
        Assertions.assertEquals("3-", BuildHeaderString.numberProvided(textFields[0]))
        Assertions.assertEquals("4-", BuildHeaderString.numberProvided(textFields[1]))
    }

    @Test
    fun checkBoxChecked_returnRegexString() {
        val checkBoxes = listOf(CheckBox(), CheckBox())
        checkBoxes[0].isSelected = true
        checkBoxes[1].isSelected = false
        Assertions.assertEquals("x-", BuildHeaderString.checkBoxChecked(checkBoxes[0]))
        Assertions.assertEquals("*-", BuildHeaderString.checkBoxChecked(checkBoxes[1]))
    }

    @ParameterizedTest
    @ValueSource(strings = ["\\d", "\\d-", "\\d-$", "([1-9]{1}|\\d{2,6})-", "234-$", "\\d$", "234$", "([1-9]{1}|\\d{2,6})$"])
    fun closeHeaderString_removeDash_true(stringInput: String) {
        val testingRegex = """^.*[^-]$""".toRegex()
        val parsedStringInput = BuildHeaderString.closeHeaderString(stringInput)
        val parsedRegex = testingRegex.matches(parsedStringInput)
        Assertions.assertTrue(parsedRegex)
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "", "^", "$", "-"])
    fun closeHeaderString_throwsIllegalArgumentException(stringInput: String) {
        assertThrows<IllegalArgumentException> {
            val testingRegex = """^.+[^-]$""".toRegex()
            val parsedStringInput = BuildHeaderString.closeHeaderString(stringInput)
            testingRegex.matches(parsedStringInput)
        }
    }

    @Test
    fun doTheListsMatch_true() {
        Assertions.assertTrue(
            BuildHeaderString.doTheListsMatch(
                testGfeSearchData.checkBoxList,
                testGfeSearchData.textFieldList
            )
        )
    }

    @Test
    fun assembleHeaderString_throwsIllegalArgumentException_listLengthNotSame() {
        val shortCheckBoxList = mutableListOf(CheckBox(), CheckBox(), CheckBox())
        val shortTextFieldList = mutableListOf(TextField(), TextField())
        val exceptionGfeSearchData = GfeSearchData(checkBoxList = shortCheckBoxList, textFieldList = shortTextFieldList)
        assertThrows<IllegalArgumentException> {
            BuildHeaderString.assembleHeaderString(exceptionGfeSearchData)
        }
    }

    @Test
    fun assembleHeaderString_returnValidString() {
        val desiredHeaderString = "HLA-Aw-*-x-3-*-2" // ! for non-existent exon
        BuildHeaderString.assembleHeaderString(testGfeSearchData)
        Assertions.assertEquals(desiredHeaderString, testGfeSearchData.regex)
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
        val tf5 = TextField()
        val tf6 = TextField("2")

        fun createCBList(): MutableList<CheckBox> {
            cb3.isSelected = true
            cb4.isSelected = true
            return mutableListOf(cb1, cb2, cb3, cb4, cb5, cb6)
        }

        val testGfeSearchData = GfeSearchData(
            checkBoxList = createCBList(),
            textFieldList = mutableListOf(tf1, tf2, tf3, tf4, tf5, tf6)
        )
    }

}
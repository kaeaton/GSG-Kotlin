package org.b12x.gfe.plugins.gfesearch.controller.regex

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.controller.GfeSearchData
import tornadofx.Component
import kotlin.IllegalArgumentException

object BuildRegexString {
    // refactor for StringBuilder

    /**
     * Assembles a string for conversion to regex.
     *
     * @params a GfeSearchData instance
     * @return a string ready to be converted to regex
     */
    fun assembleRegexString(gfeSearchData: GfeSearchData) {
        if (!doTheListsMatch(gfeSearchData.checkBoxList, gfeSearchData.textFieldList)) {
            throw IllegalArgumentException("The lists are not the same size.")
        }

        var regexString = "^${gfeSearchData.locus}w-"
        for (i in 1 until gfeSearchData.checkBoxList.size) {
            regexString += if(gfeSearchData.textFieldList[i].text.isEmpty()) {
                checkBoxChecked(gfeSearchData.checkBoxList[i])
            } else {
                numberProvided(gfeSearchData.textFieldList[i])
            }
        }
        gfeSearchData.regex = closeRegex(regexString)
    }

    /**
     * Sanity check: are the lists the same length?
     *
     * @params a list of checkboxes
     * @params a list of textfields
     * @return a boolean
     */
    fun doTheListsMatch(listCB: List<CheckBox>, listTF: List<TextField>) = listCB.size == listTF.size

    /**
     * Converts a filled textfield to appropriate regex.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the textfield containing a number
     * @return a valid regex string to append to main regex string
     */
    fun numberProvided(textField: TextField) = "${textField.text}-"

    /**
     * Converts a checked checkbox to appropriate regex.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the checkbox to convert
     * @return a valid regex string to append to main regex string
     */
    fun checkBoxChecked(checkBox: CheckBox): String {
        if (checkBox.isSelected) {
            return "([1-9]{1}|\\d{2,6})-"
        }
        return "(\\d+)-"
    }

    /**
     * Sets workshop status to a valid option.
     * This would be only for the first textfield in the textfield array.
     *
     * @return a string of a valid workshop status
     */
    fun workshopStatus(textField: TextField) = "w-"
    // Seems sort of stupid, but It needs to be here,
    // if they change that option they get no results.)

    /**
     * Closes a regex string.
     *
     * @params the string to finish
     * @return a string ready to be converted to regex
     */
    fun closeRegex(regexStringToClose: String): String {

        // matches the last character(s) of a string
        val closingDashSearchRegex = """^.+-$""".toRegex()
        val closingDollarSignSearchRegex = """^.+\$$""".toRegex()
        val closingDashAndDollarSearchRegex = """^.*-\$$""".toRegex()

        // matches a letter or number as last character of the string
        val validStringRegex = """^.+[A-Za-z0-9]+$""".toRegex()
        var finalRegex: String

        // matches: ending "-$"
        if (closingDashAndDollarSearchRegex.matches(regexStringToClose)) {
            finalRegex = regexStringToClose.subSequence(0, (regexStringToClose.length - 2)).toString()

        // matches ending "-" or "$"
        } else if (closingDashSearchRegex.matches(regexStringToClose) ||
            closingDollarSignSearchRegex.matches(regexStringToClose)
        ) {
            finalRegex = regexStringToClose.subSequence(0, (regexStringToClose.length - 1)).toString()

        // no change needed
        } else if (validStringRegex.matches(regexStringToClose)) {
            finalRegex = regexStringToClose

        // bad argument
        } else {
            throw IllegalArgumentException("Invalid String")
        }

        finalRegex = "$finalRegex$"
        return finalRegex
    }

}
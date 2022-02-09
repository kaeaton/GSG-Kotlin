package org.b12x.gfe.core.controller.regex

import javafx.scene.control.CheckBox
import java.lang.IllegalArgumentException
import java.util.*

class BuildRegexString {

    /**
     * Converts a filled textfield to appropriate regex.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the string to convert
     * @return a valid regex string to append to main regex string
     */
    fun numberProvided(int: Int) = "$int-"

    /**
     * Converts a checked checkbox to appropriate regex.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the checkbox to convert
     * @return a valid regex string to append to main regex string
     */
    fun checkBoxChecked(checkBox: CheckBox): String {
        if (checkBox.isSelected) {
            return "([1-9]{1}|\\\\d{2,6})-"
        }
        return "(\\\\d+)-"
    }

    /**
     * Converts an unchecked checkbox and no textfield content to appropriate regex.
     *
     * @return a valid regex string to append to main regex string
     */
    fun noCheckNorNumber() = "(\\\\d+)-"

    /**
     * Sets workshop status to a valid option.
     * This would be only for the first textfield in the textfield array.
     *
     * @params the current workshop status
     * @return a string of a valid workshop status
     */
    fun workshopStatus(textFieldContents: String) = "w"
    // Pretty pointless for now, new options are coming


    /**
     * Finishes a regex string.
     *
     * @params the string to finish
     * @return a string ready to be converted to regex
     */
    fun closeRegex(regexStringToClose: String): String {

        // matches a dash as the last character of the string
        val closingDashSearchRegex = """^.+-$""".toRegex()

        // matches a letter, number or _ as last character of the string
        val validStringRegex = """^.+\\w$""".toRegex()
        var finalRegex = regexStringToClose

        try {
            if (closingDashSearchRegex.matches(regexStringToClose)) {
                finalRegex = regexStringToClose.subSequence(0, (regexStringToClose.length - 1)).toString()
            } else if (validStringRegex.matches(regexStringToClose)) {
                finalRegex = regexStringToClose
            } else {
                throw IllegalArgumentException()
            }
        } catch (e: IllegalArgumentException) {
            print(e.stackTrace)
        }



        finalRegex = "$finalRegex$"
        return finalRegex
    }

}
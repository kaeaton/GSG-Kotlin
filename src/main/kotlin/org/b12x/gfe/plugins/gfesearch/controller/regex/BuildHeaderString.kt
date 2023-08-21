package org.b12x.gfe.plugins.gfesearch.controller.regex

import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import org.b12x.gfe.plugins.gfesearch.controller.GfeSearchData

object BuildHeaderString {
    // refactor for StringBuilder

    /**
     * Assembles a string for the header of a results file.
     *
     * @params a GfeSearchData instance
     * @return a string ready to be used in the header
     */
    fun assembleHeaderString(gfeSearchData: GfeSearchData) {
        if (!doTheListsMatch(gfeSearchData.checkBoxList, gfeSearchData.textFieldList)) {
            throw IllegalArgumentException("The lists are not the same size.")
        }

        var headerString = "${gfeSearchData.locus}w"
        for (i in 1 until gfeSearchData.checkBoxList.size) {
            headerString += if(gfeSearchData.textFieldList[i].text.isEmpty()) {
                checkBoxChecked(gfeSearchData.checkBoxList[i])
            } else {
                numberProvided(gfeSearchData.textFieldList[i])
            }
        }
        gfeSearchData.header = closeHeaderString(headerString)
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
     * Adds a filled textfield to the header string.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the textfield containing a number
     * @return a string to append to main header string
     */
    fun numberProvided(textField: TextField) = "${textField.text}-"

    /**
     * Converts a checked checkbox to appropriate string.
     * The textfield contents (if any) will overwrite a checked checkbox.
     *
     * @params the checkbox to convert
     * @return a string to append to main header string
     */
    fun checkBoxChecked(checkBox: CheckBox): String {
        if (checkBox.isSelected) {
            return "x-"
        }
        return "*-"
    }

    /**
     * Closes a header string.
     *
     * @params the string to finish
     * @return a string ready to be used in the results header
     */
    fun closeHeaderString(stringToClose: String): String {

        // matches the last character(s) of a string
        val closingDashSearchRegex = """^.+-$""".toRegex()
        val closingDollarSignSearchRegex = """^.+\$$""".toRegex()
        val closingDashAndDollarSearchRegex = """^.*-\$$""".toRegex()

        // matches a letter or number as last character of the string
        val validStringRegex = """^.+[A-Za-z0-9]+$""".toRegex()
        var finalString: String

        // matches: ending "-$"
        if (closingDashAndDollarSearchRegex.matches(stringToClose)) {
            finalString = stringToClose.subSequence(0, (stringToClose.length - 2)).toString()

            // matches ending "-" or "$"
        } else if (closingDashSearchRegex.matches(stringToClose) ||
            closingDollarSignSearchRegex.matches(stringToClose)
        ) {
            finalString = stringToClose.subSequence(0, (stringToClose.length - 1)).toString()

            // no change needed
        } else if (validStringRegex.matches(stringToClose)) {
            finalString = stringToClose

            // bad argument
        } else {
            throw IllegalArgumentException("Invalid String")
        }

        return finalString
    }
}
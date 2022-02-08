package org.b12x.gfe.core.controller.regex

import javafx.scene.control.CheckBox

class BuildRegexString {

    fun numberProvided(int: Int) = "$int-"

    fun checkBoxChecked(checkBox: CheckBox): String {
        if (checkBox.isSelected) {
            return "([1-9]{1}|\\\\d{2,6})-"
        }
        return "(\\\\d+)-"
    }

    fun noCheckNorNumber() = "(\\\\d+)-"
}
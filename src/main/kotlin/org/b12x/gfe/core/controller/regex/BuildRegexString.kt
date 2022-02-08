package org.b12x.gfe.core.controller.regex

import javafx.scene.control.CheckBox

class BuildRegexString {

    fun numberProvided(int: Int): String {
        return "$int-"
    }

    fun checkBoxChecked(checkBox: CheckBox): String {
        if (checkBox.isSelected) {
            return "([1-9]{1}|\\\\d{2,6})-"
        }
        return ""
    }
}
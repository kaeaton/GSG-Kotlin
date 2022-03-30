package org.b12x.gfe.plugins.gfesearch.view

import javafx.embed.swing.JFXPanel
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tornadofx.*


class GfeSearchLayoutDataTest : View("Test") {
    // necessary to initialize JavaFX
    val jfxPanel = JFXPanel()

    lateinit var checkArray: ArrayList<CheckBox>
    lateinit var textArray: ArrayList<TextField>

    override val root = vbox {
        val cb1 = CheckBox()
        val cb2 = CheckBox()
        val tf1 = TextField()
        val tf2 = TextField()

        checkArray = arrayListOf(cb1, cb2)
        textArray  = arrayListOf(tf1, tf2)
    }

    @Test
    fun resetsTheArrayLists() {
        assertEquals(2, checkArray.size)
        assertEquals(2, textArray.size)
    }
}
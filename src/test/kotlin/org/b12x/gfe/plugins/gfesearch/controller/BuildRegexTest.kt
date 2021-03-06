package org.b12x.gfe.plugins.gfesearch.controller

import io.reactivex.rxjava3.kotlin.*
import javafx.beans.Observable
import org.b12x.gfe.MyApp
import org.b12x.gfe.plugins.gfesearch.view.TestApp
import org.b12x.gfe.plugins.gfesearch.view.TestLayout
import org.b12x.gfe.utilities.preference.PrefsManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import tornadofx.*
import kotlin.system.exitProcess


class BuildRegexTest {

//    val testApp = TestApp()
    val testLayout = TestLayout()
    val textFields = testLayout.textFields
    val checkBoxes = testLayout.checkBoxes

    @Test
    fun getIntDefault() {
        assertEquals(textFields.size, checkBoxes.size)
    }

    companion object ArgumentsParameters {

        @BeforeAll
        @JvmStatic
        internal fun setup() {
//            runAsync {
                launch<TestApp>()
//            }
//            textFields.forEach {
//
//            }
//            PrefsManager.wipePref("TESTING_INT");
//            PrefsManager.wipePref("TESTING_STRING");
//            PrefsManager.wipePref("TESTING_BOOLEAN");
        }

        @AfterAll
        @JvmStatic
        internal fun after() {
//            TestApp.parentStage.hide()
//            exitProcess(0) //<TestApp>()
        }

        @JvmStatic
        fun snakeCaseArguments(): Stream<Arguments> =
            Stream.of(
                Arguments.of("addUp", "ADD_UP"),
                Arguments.of("SlowDown", "SLOW_DOWN"),
                Arguments.of("word", "WORD"),
                Arguments.of("TEST_ME", "TEST_ME")
            )

        @JvmStatic
        fun intArguments(): Stream<Arguments> =
            Stream.of(
                Arguments.of(2, 2, 4),
                Arguments.of(3, 3, 9),
                Arguments.of(2, 3, 6)
            )
    }
}


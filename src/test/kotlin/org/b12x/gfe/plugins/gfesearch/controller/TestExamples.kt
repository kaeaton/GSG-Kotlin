package org.b12x.gfe.plugins.gfesearch.controller

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream


class TestExamples {

//    val testLayout = TestLayout()
//    val textFields = testLayout.textFields
//    val checkBoxes = testLayout.checkBoxes

    @Test
    fun getIntDefault() {
//        assertEquals(textFields.size, checkBoxes.size)
    }

    companion object ArgumentsParameters {

        @BeforeAll
        @JvmStatic
        internal fun setup() {
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


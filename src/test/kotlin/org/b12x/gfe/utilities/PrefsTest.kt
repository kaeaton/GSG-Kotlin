package org.b12x.gfe.utilities

import org.b12x.gfe.utilities.Prefs.*
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class PrefsTest {

//    @Mockk
//    val prefsManager = mockk<PrefsManager>()
    private val prefs = Prefs()

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3])
    private fun setIntTest(num: Int) {
        prefs.currentTab = num
        assertEquals(num, prefs.currentTab)
    }

    @ParameterizedTest
    @MethodSource("snakeCaseParameters")
    private fun snakeCaseTest(parameter: String) {

        assertEquals(parameter, parameter)
    }

//    private fun changeCurrentTabPref(int1: Int) {
//        Prefs.currentTab = int1
//    }

//    @ParameterizedTest
//    @MethodSource("multiplyArguments")
//    fun multiplyEnumTest(int1: Int, int2: Int, expected: Int) {
//        assertEquals(expected, Prefs.currentTab)
//    }

    companion object ArgumentsParameters {
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
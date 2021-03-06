package org.b12x.gfe.utilities.preference

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class PrefsTest {

    private val prefs = Prefs()

    @Test
    fun getIntDefault() {
        assertEquals(0, prefs.testingInt)
    }

    @Test
    fun getStringDefault() {
        assertEquals("", prefs.testingString)
    }

    @Test
    fun getBooleanDefault() {
        assertEquals(false, prefs.testingBoolean)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3])
    fun setGetIntTest(num: Int) {
        prefs.testingInt = num
        assertEquals(num, prefs.testingInt)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Air", "BETA", "cdef", ""])
    fun setGetStringTest(word: String) {
        prefs.testingString = word
        assertEquals(word, prefs.testingString)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun setGetBooleanTest(boolean: Boolean) {
        prefs.testingBoolean = boolean
        assertEquals(boolean, prefs.testingBoolean)
    }

//    @ParameterizedTest
//    @MethodSource("snakeCaseArguments")
//    private fun snakeCaseTest(parameter: String) {
//
//        assertEquals(parameter, prefsMock.currentTab)
//    }

//    private fun changeCurrentTabPref(int1: Int) {
//        Prefs.currentTab = int1
//    }

//    @ParameterizedTest
//    @MethodSource("multiplyArguments")
//    fun multiplyEnumTest(int1: Int, int2: Int, expected: Int) {
//        assertEquals(expected, Prefs.currentTab)
//    }

    companion object ArgumentsParameters {

        @BeforeAll
        @JvmStatic
        internal fun setup() {
            PrefsManager.wipePref("TESTING_INT");
            PrefsManager.wipePref("TESTING_STRING");
            PrefsManager.wipePref("TESTING_BOOLEAN");
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
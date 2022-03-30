package org.b12x.gfe.core.controller.version

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class CreateNewKirVersionObjectTest {

    @Test
    fun createVersionObject_returnsVersionObject() {
        val userDirectory = System.getProperty("user.home")
        val tempVersionFolderString = "${userDirectory}/Documents/GSG/GSGData/KIR/2.7.0/"

        val resultVersion = Version(
            folder = File(tempVersionFolderString),
            name = "2.7.0",
            locusAvailable = listOf(
                "KIR2DL1",
                "KIR2DL2",
                "KIR2DL3",
                "KIR2DL4",
                "KIR2DL5A",
                "KIR2DL5B",
                "KIR2DP1",
                "KIR2DS1",
                "KIR2DS2",
                "KIR2DS3",
                "KIR2DS4",
                "KIR2DS5",
                "KIR3DL1",
                "KIR3DL2",
                "KIR3DL3",
                "KIR3DP1",
                "KIR3DS1"
            )
        )

        assertEquals(resultVersion, CreateNewKirVersionObject.createVersionObject())
    }

    @Test
    fun allKirLocuses_returnListOfLocuses() {
        val kirLocusList = listOf(
            "KIR2DL1",
            "KIR2DL2",
            "KIR2DL3",
            "KIR2DL4",
            "KIR2DL5A",
            "KIR2DL5B",
            "KIR2DP1",
            "KIR2DS1",
            "KIR2DS2",
            "KIR2DS3",
            "KIR2DS4",
            "KIR2DS5",
            "KIR3DL1",
            "KIR3DL2",
            "KIR3DL3",
            "KIR3DP1",
            "KIR3DS1"
        )

        assertEquals(kirLocusList, CreateNewKirVersionObject.allKirLocuses())
    }
}
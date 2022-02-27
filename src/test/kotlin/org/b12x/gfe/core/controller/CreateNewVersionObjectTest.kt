package org.b12x.gfe.core.controller

import org.b12x.gfe.core.controller.version.CreateNewVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File


class CreateNewVersionObjectTest {

    @Test
    fun createVersionObject_returnsVersionObject() {
        val userDirectory = System.getProperty("user.home")
        val tempVersionFolderString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/"

        val resultVersion = Version(
            folder = File(tempVersionFolderString),
            name = "2.0.1",
            locusAvailable = listOf("HLA-A", "HLA-B", "HLA-C", "HLA-DRB5")
        )

        val testVersion = CreateNewVersionObject.createVersionObject("TEST", "2.0.1")

        assertEquals(resultVersion.folder, testVersion.folder)
        assertEquals(resultVersion.name, testVersion.name)
        assertEquals(resultVersion.locusAvailable, testVersion.locusAvailable)
    }

    @Test
    fun getFolderLocation_returnCorrectFolder() {
        val userDirectory = System.getProperty("user.home")
        val tempVersionFolderString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/"

        assertEquals(File(tempVersionFolderString), CreateNewVersionObject.getFolderLocation("TEST", "2.0.1"))
    }

    @Test
    fun getFolderLocation_throwsExceptionOnInvalidData() {
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST", "2.0.2") }
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST4", "2.0.1") }
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST5", "2.0.5") }
    }
}
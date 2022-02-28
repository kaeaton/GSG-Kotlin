package org.b12x.gfe.core.controller

import org.b12x.gfe.core.controller.version.CreateNewVersionObject
import org.b12x.gfe.core.controller.version.Version
import org.junit.jupiter.api.Assertions.*
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
        val tempVersionFolderString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1"

        assertEquals(tempVersionFolderString, CreateNewVersionObject.getFolderLocation("TEST", "2.0.1"))
    }

    @Test
    fun getFolderLocation_throwsExceptionOnInvalidData() {
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST", "2.0.2") }
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST4", "2.0.1") }
        assertThrows<IllegalArgumentException> { CreateNewVersionObject.getFolderLocation("TEST5", "2.0.5") }
    }

    @Test
    fun validFolder_returnBoolean() {
        val userDirectory = System.getProperty("user.home")
        val validFolderString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/"
        val invalidFolderString = "${userDirectory}/Documents/GSG/GSGData/TEST4/2.0.4/"

        assertTrue {CreateNewVersionObject.validFolder(validFolderString)}
        assertFalse {CreateNewVersionObject.validFolder(invalidFolderString)}
    }

    @Test
    fun validFile_returnBoolean() {
        val userDirectory = System.getProperty("user.home")
        val validFileString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/HLA-A_2.0.1_GFE.csv"
        val invalidFileString = "${userDirectory}/Documents/GSG/GSGData/TEST4/2.0.4/HLA-Q_2.0.1_GFE.csv"

        assertTrue {CreateNewVersionObject.validFile(validFileString)}
        assertFalse {CreateNewVersionObject.validFile(invalidFileString)}
    }

    @Test
    fun fileContainsData_returnBoolean() {
        val userDirectory = System.getProperty("user.home")
        val validFileString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/HLA-A_2.0.1_GFE.csv"
        val invalidFileString = "${userDirectory}/Documents/GSG/GSGData/TEST4/2.0.4/HLA-DRB3_2.0.1_GFE.csv"

        assertTrue {CreateNewVersionObject.fileContainsData(File(validFileString))}
        assertFalse {CreateNewVersionObject.fileContainsData(File(invalidFileString))}
    }

    @Test
    fun getLocuses_returnsListOfAvailableLoci() {
        val locusAvailable = listOf("HLA-A", "HLA-B", "HLA-C", "HLA-DRB5")

        val userDirectory = System.getProperty("user.home")
        val folderLocation = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/"

        assertEquals(locusAvailable, CreateNewVersionObject.getLocuses(folderLocation))
    }
}
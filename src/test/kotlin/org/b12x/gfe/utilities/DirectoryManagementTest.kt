package org.b12x.gfe.utilities

import org.b12x.gfe.utilities.preference.PrefsManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class DirectoryManagementTest {

    val directoryManagement = DirectoryManagement()
    val fileManagement = FileManagement()
    val GSG_DATA = "/Documents/GSG/GSGData"
    val NESTED_DIRECTORIES = "/Documents/GSG/GSGData/TEST2/3.31.0/"
    val VERSION = "3.31.0"
    val USER_DIRECTORY = System.getProperty("user.home")

    @Test
    fun lookingForFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
        assertFalse(directoryManagement.doesFolderExist("$USER_DIRECTORY$NESTED_DIRECTORIES/test3"))
    }

    @Test
    fun creatingDataFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        assertTrue(directoryManagement.doesFolderExist(USER_DIRECTORY + NESTED_DIRECTORIES))
    }

    @Test
    fun removeEmptyFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        directoryManagement.removeFolder(USER_DIRECTORY + NESTED_DIRECTORIES)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
        assertFalse(directoryManagement.doesFolderExist(USER_DIRECTORY + NESTED_DIRECTORIES))
    }

    @Test
    fun removeFolderWithFiles() {
        fileManagement.createADataFile("TEST2", "testLocus1", VERSION)
        fileManagement.createADataFile("TEST2", "testLocus2", VERSION)
        fileManagement.createADataFile("TEST2", "testLocus3", VERSION)
        directoryManagement.removeFolder(USER_DIRECTORY + NESTED_DIRECTORIES)
        assertFalse(directoryManagement.doesFolderExist(USER_DIRECTORY + NESTED_DIRECTORIES))
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
    }

    @Test
    fun removeFolderWithSubFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        directoryManagement.removeFolder(USER_DIRECTORY + GSG_DATA + "TEST2/")
        assertTrue(directoryManagement.doesFolderExist(USER_DIRECTORY + GSG_DATA))
        assertFalse(directoryManagement.doesFolderExist(USER_DIRECTORY + GSG_DATA + "TEST2/"))
    }

    companion object ArgumentsParameters {

        @BeforeEach
//        @JvmStatic
        internal fun setup() {
            val GSG_DATA_FOLDER = System.getProperty("user.home") + "/Documents/GSG/GSGData"
            File(GSG_DATA_FOLDER + "/TEST3/").deleteRecursively()
            Files.deleteIfExists(Paths.get(GSG_DATA_FOLDER + "/TEST3/"))
            File(GSG_DATA_FOLDER + "/TEST2/").deleteRecursively()
            Files.deleteIfExists(Paths.get(GSG_DATA_FOLDER + "/TEST2/"))
        }

        @AfterAll
        @JvmStatic
        internal fun takedown() {
//            val GSG_DATA_FOLDER = System.getProperty("user.home") + "/Documents/GSG/GSGData"
//            File(GSG_DATA_FOLDER + "/TEST3/").deleteRecursively()
//            Files.deleteIfExists(Paths.get(GSG_DATA_FOLDER + "/TEST3/"))
//            File(GSG_DATA_FOLDER + "/TEST2/").deleteRecursively()
//            Files.deleteIfExists(Paths.get(GSG_DATA_FOLDER + "/TEST2/"))
        }
    }
}
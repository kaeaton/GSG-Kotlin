package org.b12x.gfe.utilities

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.File

class DirectoryManagementTest {

    val directoryManagement = DirectoryManagement()
    val fileManagement = FileManagement
    val GSG_DATA = "/Documents/GSG/GSGData"
    val NESTED_DIRECTORIES = "/Documents/GSG/GSGData/TEST2/3.31.0/"
    val VERSION = "3.31.0"
    val TEST_FILE_1 = "HLA-A_3.31.0_2019-12-19_11-28-48"
    val USER_DIRECTORY = System.getProperty("user.home")

    @Test
    fun lookingForFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
        assertFalse(directoryManagement.doesFolderExist("$USER_DIRECTORY$NESTED_DIRECTORIES/test3"))
    }

    @Test
    fun createADataFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        assertTrue(directoryManagement.doesFolderExist(USER_DIRECTORY + NESTED_DIRECTORIES))
    }

    @Test
    fun createAFolder() {
        directoryManagement.createFolder("$USER_DIRECTORY$GSG_DATA/TEST2/test", true)
        directoryManagement.createFolder("$USER_DIRECTORY$GSG_DATA/TEST2/test", true)
        directoryManagement.createFolder("$USER_DIRECTORY$GSG_DATA/TEST2/test", false)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2/test"))
//        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2/test_1"))
    }

    @Test
    fun overwriteAFolder() {
        directoryManagement.createFolder("$USER_DIRECTORY$NESTED_DIRECTORIES", true)
        fileManagement.createFile("$USER_DIRECTORY$NESTED_DIRECTORIES", TEST_FILE_1, "txt")
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$NESTED_DIRECTORIES"))
        assertTrue(fileManagement.doesFileExist("$USER_DIRECTORY$NESTED_DIRECTORIES$TEST_FILE_1.txt"))
        directoryManagement.createFolder("$USER_DIRECTORY$NESTED_DIRECTORIES", true)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$NESTED_DIRECTORIES"))
        assertFalse(fileManagement.doesFileExist("$USER_DIRECTORY$NESTED_DIRECTORIES$TEST_FILE_1.txt"))
    }

    @Test
    fun removeEmptyFolder() {
        directoryManagement.createDataFolder("TEST2", VERSION)
        directoryManagement.removeFolder(USER_DIRECTORY + NESTED_DIRECTORIES)
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
        assertFalse(directoryManagement.doesFolderExist("$USER_DIRECTORY$NESTED_DIRECTORIES"))
    }

    @Test
    fun removeFolderWithFiles() {
        fileManagement.createDataFile("TEST2", "testLocus1", VERSION)
        fileManagement.createDataFile("TEST2", "testLocus2", VERSION)
        fileManagement.createDataFile("TEST2", "testLocus3", VERSION)
        directoryManagement.removeFolder(USER_DIRECTORY + NESTED_DIRECTORIES)
        assertFalse(directoryManagement.doesFolderExist(USER_DIRECTORY + NESTED_DIRECTORIES))
        assertTrue(directoryManagement.doesFolderExist("$USER_DIRECTORY$GSG_DATA/TEST2"))
    }

    @Test
    fun removeFolderWithSubFolder() {
        directoryManagement.createFolder("$USER_DIRECTORY$GSG_DATA/TEST2/test3/test4", true)
        directoryManagement.removeFolder(USER_DIRECTORY + GSG_DATA + "TEST2/")
        assertTrue(directoryManagement.doesFolderExist(USER_DIRECTORY + GSG_DATA))
        assertFalse(directoryManagement.doesFolderExist(USER_DIRECTORY + GSG_DATA + "TEST2/"))
    }

    @BeforeEach
    internal fun setup() {
        val GSG_DATA_FOLDER = System.getProperty("user.home") + "/Documents/GSG/GSGData"
        File("$GSG_DATA_FOLDER/TEST2/").deleteRecursively()
    }

    companion object ArgumentsParameters {

        @AfterAll
        @JvmStatic
        internal fun takedown() {
            val GSG_DATA_FOLDER = System.getProperty("user.home") + "/Documents/GSG/GSGData"
            File("$GSG_DATA_FOLDER/TEST2/").deleteRecursively()
        }
    }
}
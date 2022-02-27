package org.b12x.gfe.core.controller.version

import org.b12x.gfe.utilities.DirectoryManagement
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.exists

object CreateNewVersionObject {

    private const val HEADER_LENGTH = 50  // bytes

    fun createVersionObject(loci: String, version: String): Version {
        val versionFolderLocation = getFolderLocation(loci, version)
        val locusAvailable = listOf("", "")//getLocuses(versionFolderLocation)

        return Version(
            folder = File(versionFolderLocation),
            name = version,
            locusAvailable = locusAvailable
        )
    }

    fun getFolderLocation(loci: String, version: String): String {
        val userDirectory = System.getProperty("user.home")
        val directoryManagement = DirectoryManagement()

        val localPath = directoryManagement.setLociLocation(loci)
        val folderLocation = "$userDirectory$localPath"

        if (validFolder(folderLocation)) {
            return folderLocation
        } //else {
//            throw IllegalArgumentException("Invalid loci group.")
//        }
        return ""
    }



    fun validFolder(dataLocation: String) = (Paths.get(dataLocation).exists() and File(dataLocation).isDirectory)
    fun validFile(dataLocation: String) = (Paths.get(dataLocation).exists() and fileContainsData(File(dataLocation)))
    fun fileContainsData(file: File) = file.length() > HEADER_LENGTH

//    fun getLocuses(folderLocation: String): List<String> {
//
//    }
}
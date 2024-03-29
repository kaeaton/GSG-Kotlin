package org.b12x.gfe.core.controller.version

import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.namesearch.view.NameTextAreaInfo
import org.b12x.gfe.utilities.DirectoryManagement
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.exists
import tornadofx.*

object CreateNewHlaVersionObject {

    private const val HEADER_LENGTH = 50  // bytes

    // loci refers to gene groups (HLA, KIR),
    // so the word locuses will be used instead

    /**
     * Creates an HLA version object.
     *
     * @params the loci the version is part of.
     * @params the version.
     * @return a version object.
     */
    fun createVersionObject(loci: String, version: String): Version {
        val versionFolderLocation = getFolderLocation(loci, version)
        val locusesAvailable = getLocuses(versionFolderLocation)

        return Version(
            folder = File(versionFolderLocation),
            name = version,
            locusAvailable = locusesAvailable
        )
    }

    /**
     * Finds the version's directory.
     *
     * @params the loci the version is part of.
     * @params the version.
     * @throws IllegalArgumentException for invalid loci or version.
     * @return the version's location.
     */
    fun getFolderLocation(loci: String, version: String): String {
        val directoryManagement = DirectoryManagement()
        val folderLocation = "${directoryManagement.setLociLocation(loci)}$version/"
//        val folderLocation = "$loci/$version/"
        println("I'm looking for the folder ${folderLocation}.")

        if (validFolder(folderLocation)) {
            return folderLocation
        } else {
            val gfeInformationTextArea = find(GfeTextAreaInfo::class)
            val nameInformationTextArea = find(NameTextAreaInfo::class)

            gfeInformationTextArea.infoTextArea.appendText("Selected version folder not found.\n")
            nameInformationTextArea.infoTextArea.appendText("Selected version folder not found.\n")

            return "$version Unavailable"
//            throw IllegalArgumentException("Invalid loci or version.")
        }
    }

    /**
     * Checks for a valid folder.
     *
     * @params the location of the folder.
     * @return true if valid.
     */
    fun validFolder(folderLocation: String) = (Paths.get(folderLocation).exists() and File(folderLocation).isDirectory)

    /**
     * Checks for a valid file.
     *
     * @params the location of the file.
     * @return true if valid.
     */
    fun validFile(fileLocation: String) = (Paths.get(fileLocation).exists() and fileContainsData(File(fileLocation)))

    /**
     * Checks for a valid data file.
     * It is possible to have a data file that has only headers and no data.
     * This check is to prevent including these files.
     *
     * @params the file in question.
     * @return true if the file contains data.
     */
    fun fileContainsData(file: File) = file.length() > HEADER_LENGTH

    /**
     * Gets the available usable locuses available to a version.
     *
     * @params the version folder location.
     * @return all valid locuses available.
     */
    fun getLocuses(folderLocation: String): List<String> {
        var allLocusesAvailable = ArrayList<String>()

        if (File(folderLocation).exists()) {
            File(folderLocation).listFiles()!!.forEach {// !! checked in if statement
                if (validFile(it.path) and (it.extension == "csv")) {
                    allLocusesAvailable.add(getLocusName(it.name))
                }
            }
        }

        if (allLocusesAvailable.isNotEmpty()) {
            return allLocusesAvailable.toList().sorted()
        }

        return listOf("No Data")
    }

    /**
     * Extracts the locus name from the filename.
     *
     * @params the file.
     * @return its locus name.
     */
    fun getLocusName(fileName: String) = fileName.split("_")[0]

}
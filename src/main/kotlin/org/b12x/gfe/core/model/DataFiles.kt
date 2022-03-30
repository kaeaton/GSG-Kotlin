package org.b12x.gfe.core.model

import org.b12x.gfe.utilities.DirectoryManagement
import java.io.File
import java.nio.file.Files
import java.nio.file.Files.exists
import java.nio.file.Path
import java.nio.file.Paths

object DataFiles {

    fun retrieveDataFiles(loci: String, version: String, locus: String): String {
        val directoryManagement = DirectoryManagement()

        var pathToFile = StringBuilder(directoryManagement.setLociLocation(loci))
        pathToFile.append("$version/")

        if (loci == "KIR") {
            pathToFile.append("neo4j_KIR_${version}_Download.csv")
        } else {
            pathToFile.append("neo4j_${locus}_${version}_Download.csv")
        }

        if (exists(Paths.get(pathToFile.toString()))) {
            return pathToFile.toString()// pathToFile.toString())
        } else {
            throw FileSystemException(File(pathToFile.toString()), reason = "File not found")
        }
    }
}
package org.b12x.gfe.core.controller.version

import java.io.File
import org.b12x.gfe.core.controller.loci.KirLoci

object CreateNewKirVersionObject {

    /**
     * Creates a KIR version object.
     *
     * @return a version object.
     */
    fun createVersionObject(): Version {

        val userDirectory = System.getProperty("user.home")
        val kirFolderString = "${userDirectory}/Documents/GSG/GSGData/KIR/2.7.0/"

        return Version(
            folder = File(kirFolderString),
            name = "2.7.0",
            locusAvailable = allKirLocuses()
        )
    }

    /**
     * Creates a list of all KIR loci.
     *
     * @return a list of the KIR loci.
     */
    fun allKirLocuses(): List<String> {
        val kirLocuses = ArrayList<String>()
        enumValues<KirLoci>().forEach {
            kirLocuses.add(it.fullName)
        }
        return kirLocuses.sorted().toList()
    }
}
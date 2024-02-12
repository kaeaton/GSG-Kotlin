package org.b12x.gfe.core.controller.locistate

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.utilities.DirectoryManagement
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.prefs.Preferences
import kotlin.io.path.exists
import kotlin.properties.Delegates

object PrefsLociState {

    var prefs: Preferences = PrefsCore.prefs

    var currentLociGroup: String by Delegates.observable(
        prefs.get(
            "currentLociGroup",
            "HLA"
        )
    ) { _, _, new ->
        prefs.put("currentLociGroup", new)
    }

    var currentVersionHla: String by Delegates.observable(
        prefs.get(
            "currentVersionHla",
            defaultHlaVersion()
        )
    ) { _, _, new ->
        prefs.put("currentVersionHla", new)
    }

    // checks for existing HLA directory, creates one if it doesn't exist
    // This should allow it to boot on systems without data
    private fun defaultHlaVersion() : String {
        val directoryManagement = DirectoryManagement()
        val gsgDataLocation = directoryManagement.setLociLocation("HLA")

        if (Paths.get(gsgDataLocation).exists() and (File(gsgDataLocation).list()?.isNotEmpty() == true)) {
            val fileList = File(gsgDataLocation).listFiles()
            val directories = fileList!!.filter { it.isDirectory }.toMutableList() // !! - checked in the if statement above
            directories.sort()
            return directories.last().toString().split("/").last()
        } else {
            Files.createDirectories(Paths.get(gsgDataLocation))
        }
        return ""
    }

    var currentGfeSearchVersionKir: String by Delegates.observable(
        prefs.get(
            "currentVersionKir",
            "2.7.0"
        )
    ) { _, _, new ->
        prefs.put("currentVersionKir", new)
    }
}
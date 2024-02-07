package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.GSG
import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.utilities.DirectoryManagement
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.prefs.Preferences
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.properties.Delegates

object PrefsGfeSearch {

    var prefs: Preferences = PrefsCore.prefs

    var currentGfeSearchLociGroup: String by Delegates.observable(
        prefs.get(
            "currentGfeSearchLociGroup",
            "HLA"
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchLociGroup", new)
    }

    var currentGfeSearchLocusHla: String by Delegates.observable(
        prefs.get(
            "currentGfeSearchLocusHla",
            "HLA-A"
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchLocusHla", new)
    }

    var currentGfeSearchLocusKir: String by Delegates.observable(
        prefs.get(
            "currentGfeSearchLocusKir",
            "KIR2DL1"
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchLocusKir", new)
    }

    var currentGfeSearchVersionHla: String by Delegates.observable(
        prefs.get(
            "currentGfeSearchVersionHla",
            defaultHlaVersion()
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchVersionHla", new)
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
        }

        Files.createDirectories(Paths.get(gsgDataLocation))
        return ""
    }

    var currentGfeSearchVersionKir: String by Delegates.observable(
        prefs.get(
            "currentGfeSearchVersionKir",
            "2.7.0"
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchVersionKir", new)
    }
}
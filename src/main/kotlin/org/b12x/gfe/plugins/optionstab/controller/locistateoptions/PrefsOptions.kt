package org.b12x.gfe.plugins.optionstab.controller.locistateoptions

import org.b12x.gfe.core.controller.PrefsCore
import java.util.prefs.Preferences
import kotlin.properties.Delegates

object PrefsOptions {

    var prefs: Preferences = PrefsCore.prefs

    var currentOptionsLociGroup: String by Delegates.observable(
        prefs.get(
            "currentOptionsLociGroup",
            "HLA"
        )
    ) { _, _, new ->
        prefs.put("currentOptionsLociGroup", new)
    }

    var currentOptionsLocusHla: String by Delegates.observable(
        prefs.get(
            "currentOptionsLocusHla",
            "HLA-A"
        )
    ) { _, _, new ->
        prefs.put("currentOptionsLocusHla", new)
    }

    var currentOptionsLocusKir: String by Delegates.observable(
        prefs.get(
            "currentOptionsLocusKir",
            "KIR2DL1"
        )
    ) { _, _, new ->
        prefs.put("currentGfeSearchLocusKir", new)
    }

    var currentOptionsVersionHla: String by Delegates.observable(
        prefs.get(
            "currentOptionsVersionHla",
            ""
        )
    ) { _, _, new ->
        prefs.put("currentOptionsVersionHla", new)
    }

    var currentOptionsVersionKir: String by Delegates.observable(
        prefs.get(
            "currentOptionsVersionKir",
            "2.7.0"
        )
    ) { _, _, new ->
        prefs.put("currentOptionsVersionKir", new)
    }
}
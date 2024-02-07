package org.b12x.gfe.plugins.namesearch.controller.locistatenamesearch

import org.b12x.gfe.core.controller.PrefsCore
import java.util.prefs.Preferences
import kotlin.properties.Delegates

object PrefsNameSearch {

    var prefs: Preferences = PrefsCore.prefs

    var currentNameSearchLociGroup: String by Delegates.observable(
        prefs.get(
            "currentNameSearchLociGroup",
            "HLA"
        )
    ) { _, _, new ->
        prefs.put("currentNameSearchLociGroup", new)
    }

    var currentNameSearchLocusHla: String by Delegates.observable(
        prefs.get(
            "currentNameSearchLocusHla",
            "HLA-A"
        )
    ) { _, _, new ->
        prefs.put("currentNameSearchLocusHla", new)
    }

    var currentNameSearchLocusKir: String by Delegates.observable(
        prefs.get(
            "currentNameSearchLocusKir",
            "KIR2DL1"
        )
    ) { _, _, new ->
        prefs.put("currentNameSearchLocusKir", new)
    }

    var currentNameSearchVersionHla: String by Delegates.observable(
        prefs.get(
            "currentNameSearchVersionHla",
            ""
        )
    ) { _, _, new ->
        prefs.put("currentNameSearchVersionHla", new)
    }

    var currentNameSearchVersionKir: String by Delegates.observable(
        prefs.get(
            "currentNameSearchVersionKir",
            "2.7.0"
        )
    ) { _, _, new ->
        prefs.put("currentNameSearchVersionKir", new)
    }
}
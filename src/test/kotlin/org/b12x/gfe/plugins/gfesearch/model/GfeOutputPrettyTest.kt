package org.b12x.gfe.plugins.gfesearch.model

import org.b12x.gfe.core.controller.PrefsCore
import org.junit.jupiter.api.AfterAll

class GfeOutputPrettyTest {
    @AfterAll
    fun resetPrefs() {
        /* Prefs Reset */
        PrefsCore.nuclearOption()
    }
}
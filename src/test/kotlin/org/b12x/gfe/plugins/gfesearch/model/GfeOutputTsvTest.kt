package org.b12x.gfe.plugins.gfesearch.model

import org.b12x.gfe.core.controller.PrefsCore
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

class GfeOutputTsvTest {
    private val stateContext = LociStateContextGfeSearch

    @Test
    fun headerLine1_complete_true() {

    }






    @AfterAll
    fun resetPrefs() {
        /* Prefs Reset */
        PrefsCore.nuclearOption()
    }
}
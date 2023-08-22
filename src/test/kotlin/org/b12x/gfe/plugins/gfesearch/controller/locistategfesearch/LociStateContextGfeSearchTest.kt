package org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch

import org.b12x.gfe.core.controller.PrefsCore
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LociStateContextGfeSearchTest {

    val stateContext = LociStateContextGfeSearch

    /* HLA State */
    @Test
    fun setLocus_default_true() {
        val locus = "HLA-B"
        stateContext.locus = locus
        assertEquals(locus, stateContext.locus)
    }

    @Test
    fun setVersion_default_true() {
        val version = "3.38.0"
        stateContext.version = version
        assertEquals(version, stateContext.version)
    }

    @Test
    fun setLoci_default_true() {
        val loci = "KIR"
        stateContext.loci = loci
        assertEquals(loci, stateContext.loci)
    }

    /* KIR State */
    @Test
    fun setLocus_kir_true() {
        stateContext.loci = "KIR"
        stateContext.setState("KIR")
        val locus = "KIR2DL4"
        stateContext.locus = locus
        assertEquals(locus, stateContext.locus)
    }

    @Test
    fun setVersion_kir_true() {
        stateContext.loci = "KIR"
        stateContext.setState("KIR")
        val version = "2.7.0"
        stateContext.version = version
        assertEquals(version, stateContext.version)
    }

    @Test
    fun setLoci_kirToHla_true() {
        stateContext.loci = "KIR"
        stateContext.setState("KIR")
        val loci = "HLA"
        stateContext.loci = loci
        assertEquals(loci, stateContext.loci)
    }


//    @Test
//    fun setLoci_currentState_true() {
//        val loci = "KIR"
//        stateContext.loci = loci
//        assertEquals(KirStateGfeSearch::class, stateContext.currentState)
//    }



    @AfterEach
    fun resetPrefs() {
        /* Prefs Reset */
        PrefsCore.nuclearOption()
    }
}
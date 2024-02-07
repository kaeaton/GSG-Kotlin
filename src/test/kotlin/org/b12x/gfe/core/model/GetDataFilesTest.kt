package org.b12x.gfe.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class GetDataFilesTest {

    @ParameterizedTest
    @MethodSource("searchDataArguments")
    fun retrieveDataFiles_returnsFile(loci: String, version: String, locus: String, targetResult: String) {
//        val mockedFile = Mockito.mock<Paths>(Paths.get(GetDataFiles.retrieveDataFiles(loci, version, locus)::class.java))//File(targetResult)::class.java)
//        whenever(exists(Paths.get(mockedFile))).thenReturn(true)
        assertEquals(targetResult, GetDataFiles.retrieveDataFiles(loci, version, locus))
    }

    companion object ArgumentsParameters {
        val userDirectory = System.getProperty("user.home")

        @JvmStatic
        fun searchDataArguments(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "HLA",
                    "2.0.1",
                    "HLA-DPB1",
                    "$userDirectory/Documents/GSG/GSGData/HLA/2.0.1/neo4j_HLA-DPB1_2.0.1_Download.csv"
                ),

                Arguments.of(
                    "KIR",
                    "2.7.0",
                    "KIR2DL4",
                    "$userDirectory/Documents/GSG/GSGData/KIR/2.7.0/neo4j_KIR_2.7.0_Download.csv"
                ),

                Arguments.of(
                    "HLA",
                    "3.31.0",
                    "HLA-DPB1",
                    "$userDirectory/Documents/GSG/GSGData/HLA/3.31.0/neo4j_HLA-DPB1_3.31.0_Download.csv"
                )
            )
    }
}
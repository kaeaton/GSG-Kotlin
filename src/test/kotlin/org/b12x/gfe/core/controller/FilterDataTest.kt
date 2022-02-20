package org.b12x.gfe.core.controller

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.b12x.gfe.utilities.FileManagement
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File

class FilterDataTest {

    @Test
    fun importData_returnAllDataInFile(){}


    companion object {

        val userDirectory = System.getProperty("user.home")
        val tempHlaDataFileString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/HLA-DRB1_2.0.1_Data.csv"
        val tempKirDataFileString = "${userDirectory}/Documents/GSG/GSGData/TEST/2.0.1/KIR_2.0.1_Data.csv"

        fun createTempDataFiles() {

            val hlaRows = listOf(
                listOf("2.0.1", "2022-02-20"),
                listOf("HLA-DRB1w1-1-6-15-9-12-16-1-1-1-1-1-1","HLA-DRB1*01:01:01:01"),
                listOf("HLA-DRB1w69-1-335-15-9-12-16-1-1-1-1-1-3","HLA-DRB1*01:01:01:02"),
                listOf("HLA-DRB1w0-0-0-16-0-0-0-0-0-0-0-0-0","HLA-DRB1*01:01:02"),
                listOf("HLA-DRB1w0-0-0-17-0-0-0-0-0-0-0-0-0","HLA-DRB1*01:01:03"),
                listOf("HLA-DRB1w5-5-336-296-1324-20-20-1-3-1-4-1-5","HLA-DRB1*04:01:01:01"),
                listOf("HLA-DRB1w5-5-336-296-1325-20-20-1-3-1-36-1-5","HLA-DRB1*04:01:01:02"),
                listOf("HLA-DRB1w26-5-116-296-969-20-20-1-3-1-25-1-22","HLA-DRB1*04:01:01:03"),
                listOf("HLA-DRB1w0-0-0-297-0-0-0-0-0-0-0-0-0","HLA-DRB1*04:01:02"),
                listOf("HLA-DRB1w0-0-0-298-0-0-0-0-0-0-0-0-0","HLA-DRB1*04:01:03"),
                listOf("HLA-DRB1w0-0-0-299-0-0-0-0-0-0-0-0-0","HLA-DRB1*04:01:04")
            )
            csvWriter().writeAll(hlaRows, File(tempHlaDataFileString), append = true)

            val kirRows = listOf(
                listOf("2.0.1", "2022-02-20"),
                listOf("KIR2DL4w0-4-0-4-0-11-0-0-0-3-0-8-0-5-0-16-0-1-0", "KIR2DL4*00101"),
                listOf("KIR2DL4w4-4-4-4-12-11-0-0-19-4-18-8-6-6-3-16-1-2-3", "KIR2DL4*0010201"),
                listOf("KIR2DL4w4-4-4-4-12-11-9-0-0-4-18-8-6-6-3-16-1-2-3", "KIR2DL4*0010201"),
                listOf("KIR2DL4w4-4-4-4-12-11-9-0-0-4-19-8-6-6-3-16-1-2-3", "KIR2DL4*0010202"),
                listOf("KIR3DL3w8-4-7-7-18-49-33-17-28-52-50-0-0-4-5-5-1-1-4", "KIR3DL3*012"),
                listOf("KIR3DL3w0-4-0-7-0-44-0-18-0-49-0-0-0-3-0-5-0-1-0", "KIR3DL3*01301"),
                listOf("KIR3DL3w0-4-0-7-0-44-0-18-0-48-0-0-0-3-0-5-0-1-0", "KIR3DL3*01302"),
                listOf("KIR3DL3w0-4-0-7-0-43-0-17-0-46-0-0-0-3-0-5-0-2-0", "KIR3DL3*01303"),
                listOf("KIR3DP1w10-3-12-0-12-6-10-7-17-10-0-0-0-0-0-0-0-0-1","KIR3DP1*0030101"),
                listOf("KIR3DP1w10-3-12-0-12-6-10-7-23-10-0-0-0-0-0-0-0-0-1","KIR3DP1*0030102"),
                listOf("KIR3DP1w10-3-12-0-12-5-9-7-19-10-0-0-0-0-0-0-0-0-1","KIR3DP1*0030201"),
                listOf("KIR3DP1w10-3-12-0-12-5-9-7-22-10-0-0-0-0-0-0-0-0-1","KIR3DP1*0030202")
            )

            csvWriter().writeAll(kirRows, File(tempKirDataFileString), append = true)
        }

        @BeforeAll
        @JvmStatic
        internal fun setup() {
            createTempDataFiles()
        }

        @AfterAll
        @JvmStatic
        internal fun takedown() {
            FileManagement.deleteFile(tempHlaDataFileString)
            FileManagement.deleteFile(tempKirDataFileString)
        }
    }
}
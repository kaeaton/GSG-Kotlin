package org.b12x.gfe.core.model.parsers

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import okhttp3.Response
import org.b12x.gfe.core.model.datadownload.DataDownload

object ParserHlaData {

    const val DB_HLA = "http://gfe.b12x.org/v1/imgt-versions"

    val hlaDataDownload = DataDownload("hla")

    fun parseResponse(response: Response): JsonArray<String> {
        val body = response.body?.string() ?: ""
        val stringBuilder = StringBuilder(body)
        val parser = Parser.default()

        val parsedObject = parser.parse(stringBuilder) as JsonArray<String>
//        println("HLA: ${parsedObject}")
//        parsedObject.forEach {
//            println("A line of HLA data: $it")
//        }
        return parsedObject
    }
}
package org.b12x.gfe.core.model.datadownload.gfes

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json.Default.decodeFromJsonElement
import org.b12x.gfe.core.model.datadownload.version.IncomingVersions
import org.b12x.gfe.core.view.debugtab.DebugView
import tornadofx.find
import java.io.InputStream
import java.util.*
import java.util.jar.JarInputStream

object DownloadGfes {

    val URL_HLA = "http://dash13-gfe.b12x.org/gfe/locus"
//    val URL_HLA = "http://gfe.b12x.org/gfe"
    var locus = "HLA-DPA1"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

//    val obj = Json.parseToJsonElement(stringBody) as JsonObject
//    val items = obj["items"] as JsonArray

//    for (item in items) {
//        val info = (item as JsonObject)["volumeInfo"]
//        val title = (info as JsonObject)["title"].toString()
//        println(title)
//    }
//
//    override val descriptor: SerialDescriptor = BulkGfes.serializer().descriptor
//    override fun deserialize(decoder: Decoder): BulkGfes {
//        val gfePair = decoder.decodeSerializableValue(BulkGfes.serializer())
//        return IncomingGfe(gfePair.gfe, gfePair.who)
//    }

    suspend fun getGfes() {
        val debugViewTextArea = find(DebugView::class).debuggerTextArea
        val response: HttpResponse = client.get("$URL_HLA/${locus}")
//        debugViewTextArea.appendText(response.bodyAsText())

        val obj = Json.parseToJsonElement(response.body()) as JsonObject
        val bulkGfes = obj["GFEs"] as JsonArray

        val names = mutableListOf<Pair<String, String>>()

        for (namePair in bulkGfes) {
            val gfe = (namePair as JsonObject)["GFE"].toString()
            val name = (namePair as JsonObject)["allele"].toString()
            names.add(Pair(first = name, second = gfe))
            debugViewTextArea.appendText("$gfe: $name\n")
        }

        writeToFile(names)
//        debugViewTextArea.appendText(bulkGfes.toString())
//        val bulkGfes = Json.decodeFromString<BulkGfes>(response.body<String>())
//        val bulkGfes = Json.parseToJsonElement(response.body()) // as JsonObject
//        return bulkGfes
    }

    suspend fun writeToFile (names: MutableList<Pair<String, String>>) {
        for (name in names) {

        }
    }


}
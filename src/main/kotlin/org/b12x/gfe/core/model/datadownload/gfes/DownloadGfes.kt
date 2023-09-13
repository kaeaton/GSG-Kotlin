package org.b12x.gfe.core.model.datadownload.gfes

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
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

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
//
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
//
//    override fun serialize(encoder: Encoder, value: BulkGfes) {
//        TODO("Not yet implemented")
//    }

    suspend fun getBulkGfes() {
        val debugViewTextArea = find(DebugView::class).debuggerTextArea
        val response: HttpResponse = client.get("http://gfe.b12x.org/gfe/locus/HLA-A")
        debugViewTextArea.appendText(response.bodyAsText())

        val obj = Json.parseToJsonElement(response.body()) as JsonObject
        val bulkGfes = obj["GFEs"] as JsonArray

        bulkGfes.forEach {
//            it.GFE, it.who
//            Json.decodeFromString<IncomingGfe>()
            debugViewTextArea.appendText(it.toString())
        }
        debugViewTextArea.appendText(bulkGfes.toString())
//        val bulkGfes = Json.decodeFromString<BulkGfes>(response.body<String>())
//        val bulkGfes = Json.parseToJsonElement(response.body()) // as JsonObject
//        return bulkGfes
    }

    suspend fun getGfeList() : MutableList<IncomingGfe> {
        val bulkGfes = getBulkGfes()

        val gfePairs = mutableListOf<IncomingGfe>()
//        val gfes = Json//.parseToJsonElement(bulkGfes.toString())
//            .decodeFromString<IncomingGfe>(bulkGfes.toString())
//        gfes.GFEs.forEach { k, v ->
//
//            val gfePair = IncomingGfe(gfe = k.toString(), who = v.toString())
//            gfePairs.add(gfePair)
//        }
        val debugViewTextArea = find(DebugView::class).debuggerTextArea
        debugViewTextArea.appendText(bulkGfes.toString())

        return gfePairs
    }


}
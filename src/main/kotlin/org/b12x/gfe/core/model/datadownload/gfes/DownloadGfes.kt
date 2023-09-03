package org.b12x.gfe.core.model.datadownload.gfes

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.b12x.gfe.core.view.debugtab.DebugView
import tornadofx.find

object DownloadGfes {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getGfes() {
        val debugViewTextArea = find(DebugView::class).debuggerTextArea
//        val client = HttpClient(CIO)
        val response: HttpResponse = client.get("http://gfe.b12x.org/gfe/locus/HLA-A")

        debugViewTextArea.appendText(response.body())
    }
}
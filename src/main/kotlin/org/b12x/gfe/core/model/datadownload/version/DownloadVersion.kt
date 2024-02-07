package org.b12x.gfe.core.model.datadownload.version

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.b12x.gfe.core.view.debugtab.DebugView
import tornadofx.find

object DownloadVersion {

    val HLA_VERSION_API = "http://gfe.b12x.org/ipd-imgt-hla-versions"
    val KIR_VERSION_API = "http://gfe.b12x.org/ipd-imgt-hla-versions"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    /**
     * Download available database versions.
     *
     * @return An IncomingVersions object containing a list of condensed versions.
     */
    suspend fun getVersions(loci: String): IncomingVersions {
        val api = when(loci) {
            "HLA" -> HLA_VERSION_API
            "KIR" -> KIR_VERSION_API
            else -> HLA_VERSION_API
        }

        val response: HttpResponse = client.get(api)
        val incomingVersions = Json.decodeFromString<IncomingVersions>(response.body<String>())

        return incomingVersions
    }
}
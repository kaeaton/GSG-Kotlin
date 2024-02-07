package org.b12x.gfe.core.model.datadownload.gfes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IncomingGfe(
    @SerialName("gfePair")
    val gfe: String,
    val who: String)

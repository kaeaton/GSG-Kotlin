package org.b12x.gfe.core.model.datadownload.gfes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BulkGfes(
    @SerialName("BulkGfes")
    val GFEs: ArrayList<Map<String, String>>,
    val gene: String)

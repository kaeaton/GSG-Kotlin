package org.b12x.gfe.core.model.datadownload.gfes

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class IncomingGfes(@Contextual val GFEs: String)

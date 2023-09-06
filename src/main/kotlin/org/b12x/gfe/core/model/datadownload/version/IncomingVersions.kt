package org.b12x.gfe.core.model.datadownload.version

import kotlinx.serialization.Serializable

@Serializable
data class IncomingVersions(val imgt_versions: List<String>)

package org.matthias.fetchtakehome.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiHire(val id: Int, val listId: Int, val name: String?)

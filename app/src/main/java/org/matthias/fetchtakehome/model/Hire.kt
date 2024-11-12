package org.matthias.fetchtakehome.model

import org.matthias.fetchtakehome.network.model.ApiHire

/**
 * Similar to ApiHire but the name is not nullable
 *
 * @see [ApiHire]
 */
data class Hire(val id: Int, val listId: Int, val name: String)

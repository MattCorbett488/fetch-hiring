package org.matthias.fetchtakehome.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import org.matthias.fetchtakehome.network.model.ApiHire
import kotlin.Result.Companion.success
import kotlin.Result.Companion.failure

class DefaultHiringService(
    private val client: HttpClient
) : HiringService {

    /**
     * Grab our list of hires - If it was successful, return the list and otherwise return an error
     */
    override suspend fun fetchHires(): Result<List<ApiHire>> {
        val response = client.get(Endpoints.HIRING)
        return if (response.status.isSuccess()) success(response.body()) else failure(Exception())
    }
}
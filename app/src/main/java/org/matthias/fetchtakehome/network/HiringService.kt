package org.matthias.fetchtakehome.network

import org.matthias.fetchtakehome.network.model.ApiHire

interface HiringService {

    suspend fun fetchHires(): Result<List<ApiHire>>
}
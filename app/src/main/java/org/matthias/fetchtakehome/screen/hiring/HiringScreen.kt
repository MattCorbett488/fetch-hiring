package org.matthias.fetchtakehome.screen.hiring

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.matthias.fetchtakehome.network.HiringService
import org.matthias.fetchtakehome.network.model.ApiHire

@Composable
fun HiringScreen(viewModel: HiringViewModel = viewModel(), modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.fetchHires() }

    when (state) {
        HiringScreenState.Loading -> Box(modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        is HiringScreenState.Error -> {
            val exception = (state as HiringScreenState.Error).exception
            HireRetry(
                text = "There was an error fetching hires: ${exception.message}",
                onRetry = viewModel::fetchHires,
                modifier = modifier
            )
        }

        is HiringScreenState.HiringList -> {
            val groupedHires = (state as HiringScreenState.HiringList).hiresByListId
            HireList(groupedHires, modifier)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL)
@Composable
fun HiringScreenPreview() {
    val fakeService = object : HiringService {
        override suspend fun fetchHires(): Result<List<ApiHire>> {
            return Result.failure(Exception("Oh no"))
        }
    }
    val viewModel = HiringViewModel(fakeService)
    Scaffold(
        modifier = Modifier
            .padding(36.dp)
            .fillMaxSize()
    ) {
        HiringScreen(
            viewModel,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL)
@Composable
fun HiringScreenLoadingPreview() {
    val fakeService = object : HiringService {
        var injectableResult: Result<List<ApiHire>> = Result.failure(Exception("Oh no"))
        override suspend fun fetchHires(): Result<List<ApiHire>> {
            delay(100_000)
            return injectableResult
        }
    }
    val viewModel = HiringViewModel(fakeService)
    Scaffold(
        modifier = Modifier
            .padding(36.dp)
            .fillMaxSize()
    ) {
        HiringScreen(
            viewModel,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL)
@Composable
fun HiringScreenSuccessPreview() {
    val fakeService = object : HiringService {
        override suspend fun fetchHires(): Result<List<ApiHire>> {
            return Result.success(
                listOf(
                    ApiHire(1, 1, "A name"),
                    ApiHire(2, 2, "Another name"),
                    ApiHire(3, 1, "A third name"),
                    ApiHire(5, 2, "Cat 2"),
                    ApiHire(6, 2, "Another in cat 2"),
                    ApiHire(4, 3, "Last name")
                )
            )
        }
    }
    val viewModel = HiringViewModel(fakeService)
    Scaffold(
        modifier = Modifier
            .padding(36.dp)
            .fillMaxSize()
    ) {
        HiringScreen(
            viewModel,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}
package org.matthias.fetchtakehome.screen.hiring

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.matthias.fetchtakehome.network.HiringService
import org.matthias.fetchtakehome.network.model.ApiHire

@OptIn(ExperimentalCoroutinesApi::class)
class HiringViewModelTest {

    @RelaxedMockK
    private lateinit var service: HiringService

    private lateinit var dispatcher: TestDispatcher

    private lateinit var viewModel: HiringViewModel

    private val dummyHires = listOf(
        ApiHire(1, 1, "A name"),
        ApiHire(2, 2, "Another name"),
        ApiHire(3, 1, "A third name"),
        ApiHire(5, 2, "Cat 2"),
        ApiHire(6, 2, "Another in cat 2"),
        ApiHire(4, 3, "Last name"),
        // These two are bad entries and should be filtered
        ApiHire(7, 3, null),
        ApiHire(8, 2, ""),
    )

    @Before
    fun setup() {
        dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)

        MockKAnnotations.init(this)
        viewModel = HiringViewModel(service)
    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should filter out hires with null or empty names`() = runTest {
        coEvery { service.fetchHires() } returns Result.success(dummyHires)
        viewModel.fetchHires()

        val result = viewModel.state.value
        assert(result is HiringScreenState.HiringList)
        val hires = (result as HiringScreenState.HiringList).hiresByListId

        // Should have list IDs 1-3, so 3 entries total
        assertEquals(3, hires.size)
        val totalHires = hires.entries.flatMap { it.value }
        // Should filter out the null and blank names
        assertEquals(dummyHires.size - 2, totalHires.size)
        // And making sure it filtered out 7 and 8
        assert(totalHires.all { it.id in 1..6 })
    }

    @Test
    fun `should send Loading state by default`() = runTest {
        assert(viewModel.state.value is HiringScreenState.Loading)
    }

    @Test
    fun `should send error state on failure`() {
        val exc = IllegalStateException("Oh no")
        coEvery { service.fetchHires() } returns Result.failure(exc)

        viewModel.fetchHires()

        val result = viewModel.state.value
        assert(result is HiringScreenState.Error)
        val error = (result as HiringScreenState.Error).exception
        assertEquals(exc, error)
    }
}
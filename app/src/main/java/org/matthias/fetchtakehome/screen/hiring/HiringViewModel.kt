package org.matthias.fetchtakehome.screen.hiring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.matthias.fetchtakehome.di.Dependencies
import org.matthias.fetchtakehome.model.Hire
import org.matthias.fetchtakehome.network.HiringService

class HiringViewModel(
    private val hiringService: HiringService = Dependencies.hiringService
) : ViewModel() {
    private val _state = MutableStateFlow<HiringScreenState>(HiringScreenState.Loading)
    val state: StateFlow<HiringScreenState> = _state

    fun fetchHires() {
        viewModelScope.launch {
            hiringService.fetchHires()
                .onFailure { _state.emit(HiringScreenState.Error(it)) }
                .onSuccess { rawList ->
                    // Filter out null names and then sort by listId and name
                    val hireList = rawList.mapNotNull {
                        val name = it.name
                        if (name.isNullOrBlank()) return@mapNotNull null
                        Hire(it.id, it.listId, name)
                    }.sortedWith(compareBy({ it.listId }, { it.name }))
                        .groupBy { it.listId }

                    _state.emit(HiringScreenState.HiringList(hireList))
                }
        }
    }
}
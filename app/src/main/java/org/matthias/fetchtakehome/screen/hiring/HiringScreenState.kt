package org.matthias.fetchtakehome.screen.hiring

import org.matthias.fetchtakehome.model.Hire
import java.lang.Exception

sealed interface HiringScreenState {
    data object Loading : HiringScreenState
    data class Error(val exception: Throwable): HiringScreenState
    data class HiringList(val hiresByListId: Map<Int, List<Hire>>): HiringScreenState
}

package uz.gita.task.presentation.viewModel.impls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.task.domain.repository.interfaces.ContentRepository
import uz.gita.task.presentation.viewModel.interfaces.HomeViewModel
import uz.gita.task.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: ContentRepository
) :ViewModel(),HomeViewModel{
    override val categoriesTabFlow = eventValueFlow<List<String>>()

    init {
        repository.categoriesTabLayout().onEach {
            categoriesTabFlow.tryEmit(it)
        }.launchIn(viewModelScope)
    }


}
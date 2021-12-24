package uz.gita.task.presentation.viewModel.impls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.task.data.remote.response.ArticlesItem
import uz.gita.task.domain.repository.interfaces.ContentRepository
import uz.gita.task.presentation.viewModel.interfaces.MainPageViewModel
import uz.gita.task.utils.eventFlow
import uz.gita.task.utils.eventValueFlow
import javax.inject.Inject


@HiltViewModel
class MainPageViewModelImpl @Inject constructor(
    private val repository:ContentRepository
):ViewModel(),MainPageViewModel {

    override val contentLoad = eventValueFlow<PagingData<ArticlesItem>>()
    override val pagingAdapterFlow = eventFlow()

    init {
        pagingAdapterFlow.tryEmit(Unit)
    }

    override fun getCategoryName(categoryName: String) {
        repository.itemsAll(categoryName,viewModelScope).onEach {
            contentLoad.emit(it)
        }.launchIn(viewModelScope)


    }

}
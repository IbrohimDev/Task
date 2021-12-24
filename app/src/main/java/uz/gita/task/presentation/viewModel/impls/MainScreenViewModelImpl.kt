package uz.gita.task.presentation.viewModel.impls

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.gita.task.presentation.viewModel.interfaces.MainScreenViewModel
import uz.gita.task.utils.eventFlow
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModelImpl @Inject constructor():ViewModel(),MainScreenViewModel {
    override val bottomAdapterFlow = eventFlow()

    init {
        bottomAdapterFlow.tryEmit(Unit)
    }
}
package uz.gita.task.presentation.viewModel.interfaces

import kotlinx.coroutines.flow.Flow

interface MainScreenViewModel {
    val bottomAdapterFlow: Flow<Unit>
}
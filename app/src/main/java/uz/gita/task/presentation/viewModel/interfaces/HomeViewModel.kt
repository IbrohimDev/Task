package uz.gita.task.presentation.viewModel.interfaces

import kotlinx.coroutines.flow.Flow

interface HomeViewModel {
  val categoriesTabFlow:Flow<List<String>>
}
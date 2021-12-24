package uz.gita.task.presentation.viewModel.interfaces

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.task.data.remote.response.ArticlesItem

interface MainPageViewModel {

    val contentLoad:Flow<PagingData<ArticlesItem>>
    val pagingAdapterFlow:Flow<Unit>

    fun getCategoryName(categoryName:String)

}
package uz.gita.task.domain.repository.interfaces

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.task.data.remote.response.ArticlesItem

interface ContentRepository {

    fun itemsAll(category:String,scope: CoroutineScope):Flow<PagingData<ArticlesItem>>
   fun categoriesTabLayout():Flow<List<String>>
}
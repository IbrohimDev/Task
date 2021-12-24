package uz.gita.task.domain.repository.impls

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.task.data.dataSource.AllDataSource
import uz.gita.task.data.remote.api.Api
import uz.gita.task.data.remote.response.ArticlesItem
import uz.gita.task.domain.repository.interfaces.ContentRepository
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val config: PagingConfig,
    private val list:ArrayList<String>
) : ContentRepository {
    override fun itemsAll(category: String, scope: CoroutineScope): Flow<PagingData<ArticlesItem>> =
        Pager(config) {
            AllDataSource(api, category)
        }.flow.cachedIn(scope)

    override fun categoriesTabLayout(): Flow<List<String>> = flow {
        list.add("general")
        list.add("business")
        list.add("entertainment")
        list.add("health")
        list.add("science")
        list.add("sports")
        list.add("technology")
        emit(list)
    }

}
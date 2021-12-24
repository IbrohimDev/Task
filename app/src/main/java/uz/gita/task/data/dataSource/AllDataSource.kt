package uz.gita.task.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.gita.task.BuildConfig
import uz.gita.task.BuildConfig.*
import uz.gita.task.data.remote.api.Api
import uz.gita.task.data.remote.response.ArticlesItem
import uz.gita.task.data.remote.response.Response
import javax.inject.Inject

class AllDataSource @Inject constructor (
  val api:Api,
  val category:String
) :PagingSource<Int,ArticlesItem>(){
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
          val anchorPosition = state.anchorPosition?:return null
        val page = state.closestPageToPosition(anchorPosition)?:return null

       return page.prevKey?.plus(1)?:page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val response = api.getAllContentsByCategory("bbc-news","popularity",category,pageSize,page,
            API_KEY
        )
        return if(response.isSuccessful){
            val article = response.body()
            val nextKey = if (article?.articles?.size!! < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(article.articles, prevKey, nextKey)
        }else{
            LoadResult.Error(Throwable("Ulanishda xatolik bo'ldi"))
        }
    }
}
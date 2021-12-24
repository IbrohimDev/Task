package uz.gita.task.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("/v2/everything")
    suspend fun getAllContentsByCategory(@Query("sources") source:String,
           @Query("sortBy") sortBy:String,@Query("q") category:String,
                                         @Query("pageSize") pageSize:Int,
                                         @Query("page") page:Int,
                                         @Query("apiKey") apiKey:String):Response<uz.gita.task.data.remote.response.Response>

}
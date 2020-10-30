package com.challenge.nyt.data.database


import com.challenge.nyt.model.article.ArticleModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleInterface {
    @GET("mostviewed/all-sections/7.json")
    fun getArticle(@Query("api-key") apiKey: String): Observable<ArticleModel>
}
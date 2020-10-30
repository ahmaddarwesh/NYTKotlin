package com.challenge.nyt.data.database




import com.challenge.nyt.data.configuration.Config
import com.challenge.nyt.model.article.ArticleModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DatabaseClient {
    private var todoInterface: ArticleInterface? = null
    private var INSTANT: DatabaseClient? = null


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        todoInterface = retrofit.create(ArticleInterface::class.java)
    }

    fun getINSTANCE(): DatabaseClient {
        if (INSTANT == null) {
            INSTANT = DatabaseClient()
        }
        return INSTANT!!
    }

    fun getArticles(): Observable<ArticleModel> {
        return todoInterface!!.getArticle(Config.ApiKey);
    }

}
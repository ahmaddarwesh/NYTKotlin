package com.challenge.nyt.ui.engin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.nyt.data.database.DatabaseClient
import com.challenge.nyt.model.article.ArticleModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel : ViewModel() {

    var  mutableLiveData = MutableLiveData<ArticleModel>()
    var TAG = "TAG_INFO"

    fun getDataFromServer() {
        val observable = DatabaseClient().getINSTANCE().getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val observer = object : Observer<ArticleModel> {
            override fun onComplete() {
                Log.d(TAG, "Completed!")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "Subscribed!")
            }

            override fun onNext(value: ArticleModel) {
                mutableLiveData.value  =value
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Error! ${e.message}")
            }


        }
        observable.subscribe(observer)
    }
}
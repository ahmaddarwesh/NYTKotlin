package com.challenge.nyt.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.nyt.R
import com.challenge.nyt.model.article.ResultsItem
import com.challenge.nyt.ui.engin.MainViewModel
import com.challenge.nyt.ui.engin.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mainViewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(MainViewModel::class.java)
        mainViewModel.getDataFromServer()

        val adapter = ArticlesAdapter(this)
        recycllerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycllerView.adapter = adapter


        mainViewModel.mutableLiveData.observe(this, Observer {items ->
            val list = ArrayList<ResultsItem>()
            items.results.forEach {
                list.add(it)
            }
            adapter.setList(list)
        })

    }
}
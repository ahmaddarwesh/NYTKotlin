package com.challenge.nyt

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.challenge.nyt.data.database.DatabaseClient
import com.challenge.nyt.model.article.ArticleModel
import com.challenge.nyt.ui.engin.ArticlesAdapter
import com.challenge.nyt.ui.main.MainActivity
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainTest {


    @Test
    fun testingMainActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
        onView(withId(R.id.recycllerView)).check(matches(isDisplayed()))

        DatabaseClient().getINSTANCE()
            .getArticles()
            .subscribeOn(io())

        object : Observer<ArticleModel> {
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(value: ArticleModel) {
                onView(withId(R.id.recycllerView)).perform(
                    RecyclerViewActions.scrollToPosition<ArticlesAdapter.MyHolder>(4)
                )
                onView(withId(R.id.recycllerView)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<ArticlesAdapter.MyHolder>(2, click())
                )
                onView(withId(R.id.articleDetailsActivity)).check(matches(isDisplayed()))
            }

            override fun onError(e: Throwable) {
                Log.d("errorTesting", e.message.toString())
            }
        }

    }


}
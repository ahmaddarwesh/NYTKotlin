package com.challenge.nyt.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.nyt.R
import kotlinx.android.synthetic.main.activity_article_details.*


class ArticleDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        d_byline.text = intent.extras!!.getString("byline")
        section.text = intent.extras!!.getString("section")
        subsection.text = intent.extras!!.getString("subsection")
        type.text = intent.extras!!.getString("type")
        date.text = intent.extras!!.getString("date")
        url.text = intent.extras!!.getString("url")
        d_abstract.text = intent.extras!!.getString("abstract")
        d_title.text =  intent.extras!!.getString("title")

        url.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.text.toString()))
            startActivity(intent);
        }
    }
}
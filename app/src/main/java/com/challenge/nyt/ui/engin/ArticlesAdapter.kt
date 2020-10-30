package com.challenge.nyt.ui.engin


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.challenge.nyt.R
import com.challenge.nyt.model.article.ResultsItem
import com.challenge.nyt.ui.details.ArticleDetails


class ArticlesAdapter(var conx: Context) :
    RecyclerView.Adapter<ArticlesAdapter.MyHolder>() {

    var myData = ArrayList<ResultsItem>()

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {


        var id = view.findViewById<TextView>(R.id.art_id)!!
        var title = view.findViewById<TextView>(R.id.title)!!
        var byline = view.findViewById<TextView>(R.id.byline)!!
        var publishedDate = view.findViewById<TextView>(R.id.published_date)!!
        var name = view.findViewById<TextView>(R.id.name)!!
        var itemClickable = view.findViewById<ConstraintLayout>(R.id.item_article)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val myView = LayoutInflater.from(conx).inflate(R.layout.article_card, parent, false)
        return MyHolder(myView)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.text = myData[position].title
        holder.byline.text = myData[position].byline
        holder.publishedDate.text = myData[position].publishedDate;
        holder.name.text = myData[position].section;
        holder.id.text = myData[position].id;
        holder.itemClickable.setOnClickListener {
            val i = Intent(conx, ArticleDetails::class.java)
            i.putExtra("id", myData[position].id)
            i.putExtra("title", myData[position].title)
            i.putExtra("byline", myData[position].byline)
            i.putExtra("abstract", myData[position].getabstractText()) //

            i.putExtra("section", myData[position].section)
            i.putExtra("subsection", myData[position].subsection)
            i.putExtra("type", myData[position].type)
            i.putExtra("date", myData[position].publishedDate)
            i.putExtra("url", myData[position].url)
            conx.startActivity(i)
        }

    }

    fun setList(list:ArrayList<ResultsItem>){
        this.myData = list
        notifyDataSetChanged()
    }

}
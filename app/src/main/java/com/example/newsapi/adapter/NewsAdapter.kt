package com.example.newsapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.R
import com.example.newsapi.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var newsList: List<Article>): RecyclerView.Adapter<NewsAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(news: Article) {
            itemView.txtTitle.text = news.title
            itemView.txtContent.text = news.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(newsList[position])
    }
}
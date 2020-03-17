package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.adapter.NewsAdapter
import com.example.newsapi.api.NewsApiInterface
import com.example.newsapi.model.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    fun getNews() {
        var BASE_URL = "https://newsapi.org/"

        var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var retrofitService = retrofit.create(NewsApiInterface::class.java)

        var apiCall = retrofitService.getNews("us","business","bf11b1d2ccd94155bdffe2d638d2540d")

        apiCall.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Failure Connection >>>>", t.toString())
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                var newsList = response.body()?.articles
                Log.d("Success Connection >>>>", newsList.toString())
                var newsAdapter = newsList?.let { NewsAdapter(it) }
                recyclernews.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclernews.adapter = newsAdapter
            }
        })

    }
}
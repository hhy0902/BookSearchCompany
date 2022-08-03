package com.example.booksearchcompany.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearchcompany.BuildConfig
import com.example.booksearchcompany.R
import com.example.booksearchcompany.RetrofitServices
import com.example.booksearchcompany.databinding.FragmentNewbookBinding
import com.example.booksearchcompany.databinding.FragmentSearchBinding
import com.example.booksearchcompany.newbook.NewBookAdapter
import com.example.booksearchcompany.search.model.SearchBookItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    lateinit var searchBookAdaptet : SearchBookAdapter

    var start = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSearchBinding = FragmentSearchBinding.bind(view)

        searchBookAdaptet = SearchBookAdapter()
        fragmentSearchBinding.searchRecyclerView.layoutManager = LinearLayoutManager(activity)
        fragmentSearchBinding.searchRecyclerView.adapter = searchBookAdaptet

        fragmentSearchBinding.searchButton.setOnClickListener {
            val searchText = fragmentSearchBinding.searchEditText.text.toString()
            searchBook(searchText)
        }

        fragmentSearchBinding.leftButton.setOnClickListener {
            start--
            val searchText = fragmentSearchBinding.searchEditText.text.toString()
            searchBook(searchText)
        }
        fragmentSearchBinding.rightButton.setOnClickListener {
            start++
            val searchText = fragmentSearchBinding.searchEditText.text.toString()
            searchBook(searchText)
        }


    }

    private fun searchBook(searchText : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.aladin.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttpClient())
            .build()
        val retrofitServices = retrofit.create(RetrofitServices::class.java)
        retrofitServices.getSearchBookItem(searchText, start.toString()).enqueue(object : Callback<SearchBookItem> {
            override fun onResponse(call: Call<SearchBookItem>, response: Response<SearchBookItem>) {
                val item = response.body()
                val itemList = item?.item

                searchBookAdaptet.submitList(itemList)

                itemList?.forEach {
                    Log.d("itemList search","$it")
                }

            }

            override fun onFailure(call: Call<SearchBookItem>, t: Throwable) {
                Log.d("onFailure error", "${t.message}")
            }
        })
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

}














































package com.example.booksearchcompany.newbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearchcompany.BookDetailActivity
import com.example.booksearchcompany.BuildConfig
import com.example.booksearchcompany.R
import com.example.booksearchcompany.RetrofitServices
import com.example.booksearchcompany.bestseller.BestSellerAdapter
import com.example.booksearchcompany.databinding.FragmentBestsellerBinding
import com.example.booksearchcompany.databinding.FragmentNewbookBinding
import com.example.booksearchcompany.newbook.model.NewBookItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewBookFragment : Fragment(R.layout.fragment_newbook) {

    lateinit var newBookAdaptet : NewBookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentNewBookBinding = FragmentNewbookBinding.bind(view)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.aladin.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttpClient())
            .build()
        val retrofitServices = retrofit.create(RetrofitServices::class.java)

        newBookAdaptet = NewBookAdapter(newBookItemClick = { item ->
            Toast.makeText(activity, "${item.title}", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, BookDetailActivity::class.java)
            intent.putExtra("title",item.title)
            intent.putExtra("imageCover", item.cover)
            startActivity(intent)
        })
        fragmentNewBookBinding.newBookRecyclerView.adapter = newBookAdaptet
        fragmentNewBookBinding.newBookRecyclerView.layoutManager = LinearLayoutManager(activity)

        retrofitServices.getNewBookItem().enqueue(object : Callback<NewBookItem> {
            override fun onResponse(call: Call<NewBookItem>, response: Response<NewBookItem>) {
                val item = response.body()
                val itemList = item?.item

                newBookAdaptet.submitList(itemList)

                itemList?.forEach {
                    Log.d("itemList new","$it")
                }
            }

            override fun onFailure(call: Call<NewBookItem>, t: Throwable) {
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








































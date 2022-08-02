package com.example.booksearchcompany.bestseller

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearchcompany.BuildConfig
import com.example.booksearchcompany.R
import com.example.booksearchcompany.RetrofitObject
import com.example.booksearchcompany.RetrofitServices
import com.example.booksearchcompany.bestseller.model.BestSellerItem
import com.example.booksearchcompany.databinding.FragmentBestsellerBinding
import com.example.booksearchcompany.databinding.FragmentMypageBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

class BestSellerFragment : Fragment(R.layout.fragment_bestseller){

    lateinit var bestSellerAdaptet : BestSellerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentBestSellerBinding = FragmentBestsellerBinding.bind(view)

        val address = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=Bestseller&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101"

        Log.d("BestSellerFragment", "BestSellerFragment")

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.aladin.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttpClient())
            .build()

        bestSellerAdaptet = BestSellerAdapter()
        fragmentBestSellerBinding.bestSellerRecyclerView.layoutManager = LinearLayoutManager(activity)

        val retrofitService = retrofit.create(RetrofitServices::class.java)
        retrofitService.getBestSellerItem().enqueue(object : Callback<BestSellerItem> {
            override fun onResponse(call: Call<BestSellerItem>, response: Response<BestSellerItem>) {
                if (response.isSuccessful) {
                    val item = response.body()
                    val itemList = item?.item

                    fragmentBestSellerBinding.bestSellerRecyclerView.adapter = bestSellerAdaptet
                    bestSellerAdaptet.submitList(itemList)

                    itemList?.forEach {
                        Log.d("itemList","$it")
                    }

                }
            }

            override fun onFailure(call: Call<BestSellerItem>, t: Throwable) {
                Log.d("onFailure error", "${t.message}")
            }

        })

//        try {
//            val response = RetrofitObject.retrofitService.getBestSellerItemResponse()
//            if (response.isSuccessful) {
//                val body = response.body()
//                val itemList = body?.item
//
//                itemList?.forEach {
//                    Log.d("itemList", "$it")
//                }
//            }
//        } catch (e : Exception) {
//            e.printStackTrace()
//        }

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










































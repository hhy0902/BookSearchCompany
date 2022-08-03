package com.example.booksearchcompany

import com.example.booksearchcompany.bestseller.model.BestSellerItem
import com.example.booksearchcompany.newbook.model.NewBookItem
import com.example.booksearchcompany.search.model.SearchBookItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=Bestseller&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getBestSellerItem(): Call<BestSellerItem>

    @GET("ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=ItemNewSpecial&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getNewBookItem(): Call<NewBookItem>

    @GET("ttb/api/ItemSearch.aspx?ttbkey=ttbhhy090200921001&MaxResults=200&SearchTarget=Book&output=JS&Version=20131101")
    fun getSearchBookItem(
        @Query("Query") Query : String,
        @Query("start") start : String
    ) : Call<SearchBookItem>




    @GET("ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=Bestseller&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getBestSellerItemResponse(): Response<BestSellerItem>

}








































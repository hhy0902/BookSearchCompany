package com.example.booksearchcompany

import com.example.booksearchcompany.bestseller.model.BestSellerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServices {

    @GET("ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=Bestseller&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getBestSellerItem(): Call<BestSellerItem>

    @GET("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=ItemNewSpecial&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getNewBookItem(): Call<BestSellerItem>


    @GET("ttb/api/ItemList.aspx?ttbkey=ttbhhy090200921001&QueryType=Bestseller&MaxResults=200&start=1&SearchTarget=Book&output=JS&Version=20131101")
    fun getBestSellerItemResponse(): Response<BestSellerItem>

}








































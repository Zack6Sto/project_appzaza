package com.example.project_appzaza.api

import androidx.lifecycle.LiveData
import com.example.project_appzaza.model.BlogPost
import com.example.project_appzaza.model.User
import com.example.project_appzaza.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

}
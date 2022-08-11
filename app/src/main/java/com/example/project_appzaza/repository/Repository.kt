package com.example.project_appzaza.repository

import androidx.lifecycle.LiveData
import com.example.project_appzaza.api.MyRetrofitBuilder
import com.example.project_appzaza.model.BlogPost
import com.example.project_appzaza.model.User
import com.example.project_appzaza.ui.main.state.MainViewState
import com.example.project_appzaza.util.ApiSuccessResponse
import com.example.project_appzaza.util.DataState
import com.example.project_appzaza.util.GenericApiResponse

object Repository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<BlogPost>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        blogPosts = response.body,
                        user = null
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return MyRetrofitBuilder.apiService.getBlogPosts()
            }

        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<User, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        blogPosts = null,
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return MyRetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}
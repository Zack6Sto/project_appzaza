package com.example.project_appzaza.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.project_appzaza.model.BlogPost
import com.example.project_appzaza.model.User
import com.example.project_appzaza.repository.Repository
import com.example.project_appzaza.ui.main.state.MainStateEvent
import com.example.project_appzaza.ui.main.state.MainViewState
import com.example.project_appzaza.ui.main.state.MainStateEvent.*
import com.example.project_appzaza.util.AbsentLiveData
import com.example.project_appzaza.util.DataState

class MainViewModel : ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState


    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        when(stateEvent){

            is GetBlogPostsEvent -> {
                return Repository.getBlogPosts()
            }

            is GetUserEvent -> {
                return Repository.getUser(stateEvent.userId)
            }

            is None ->{
                return AbsentLiveData.create()
            }
        }
    }

    fun setBlogListData(blogPosts: List<BlogPost>){
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let{
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent){
        val state: MainStateEvent
        state = event
        _stateEvent.value = state
    }
}
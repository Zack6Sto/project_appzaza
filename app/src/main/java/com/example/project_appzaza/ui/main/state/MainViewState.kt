package com.example.project_appzaza.ui.main.state

import com.example.project_appzaza.model.BlogPost
import com.example.project_appzaza.model.User

data class MainViewState(

    var blogPosts: List<BlogPost>? = null,

    var user: User? = null

)
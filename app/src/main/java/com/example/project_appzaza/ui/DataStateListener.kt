package com.example.project_appzaza.ui

import com.example.project_appzaza.util.DataState

interface DataStateListener {
    fun onDataStateChange(dataState: DataState<*>?)
}
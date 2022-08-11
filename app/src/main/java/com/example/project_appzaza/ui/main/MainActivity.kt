package com.example.project_appzaza.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.project_appzaza.R
import com.example.project_appzaza.databinding.ActivityMainBinding
import com.example.project_appzaza.ui.DataStateListener
import com.example.project_appzaza.util.DataState

class MainActivity : AppCompatActivity(),
    DataStateListener
{
    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        showMainFragment()
    }

    fun showMainFragment(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    MainFragment(),
                    "MainFragment"
                )
                .commit()
        }
    }

    fun handleDataStateChange(dataState: DataState<*>?){
        dataState?.let{
            // Handle loading
            showProgressBar(dataState.loading)

            // Handle Message
            dataState.message?.let{ event ->
                event.getContentIfNotHandled()?.let { message ->
                    showToast(message)
                }
            }
        }
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }


}
package com.juliarman.viewmodellearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = adapter
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)



        btnCity.setOnClickListener {

            val city = edtCity.text.toString()

            if (city.isEmpty()) return@setOnClickListener

            showLoading(true)
            mainViewModel.setWeather(city)

        }

        mainViewModel.getWeather().observe(this, Observer { WeatherItems ->

            if (WeatherItems != null){

                adapter.setData(WeatherItems)
                showLoading(false)
            }

        }


        )

    }



    private fun showLoading(state: Boolean){

        if (state){

            progressBar.visibility = View.VISIBLE
        } else{

            progressBar.visibility = View.GONE
        }
    }
}
package com.juliarman.viewmodellearn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val mData = ArrayList<WeatherItems>()

    fun setData(items: ArrayList<WeatherItems>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    inner class WeatherViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(weatherItems: WeatherItems) {

            with(itemView){

                textCity.text = weatherItems.name
                textDesc.text = weatherItems.description
                textTemp.text = weatherItems.temperature

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(mView)

    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        holder.bind(mData[position])

    }

    override fun getItemCount(): Int = mData.size


}
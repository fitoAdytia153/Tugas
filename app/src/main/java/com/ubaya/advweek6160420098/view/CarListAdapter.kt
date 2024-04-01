package com.ubaya.advweek6160420098.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek6160420098.R
import com.ubaya.advweek6160420098.databinding.CarListItemsBinding
import com.ubaya.advweek6160420098.model.Car
import com.ubaya.advweek6160420098.util.loadImage

class CarListAdapter(private val carList: ArrayList<Car>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(val binding: CarListItemsBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newCarList: ArrayList<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.binding.txtType.text = car.type
        holder.binding.txtModel.text = car.model
        holder.binding.txtYear.text = car.year.toString()
        holder.binding.imgStory.loadImage(carList[position].carUrl, holder.binding.progressBar)

        var imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)

        holder.binding.btnDetail.setOnClickListener {
            val action = CarListFragmentDirections.actionCarDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = carList.size


}


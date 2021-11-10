package com.example.flickrbrowserapproomoptional

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.Retrofit.Photo
import com.example.flickrbrowserapproomoptional.databinding.ItemRowBinding


class RVAdapterApi(val photos:ArrayList<Photo>, val fragment: homeFragment):RecyclerView.Adapter<RVAdapterApi.RVHolder>() {
    class RVHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val binding= ItemRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        val server=photos[position].server
        val id=photos[position].id
        val secret=photos[position].secret
        val title=photos[position].title
        val imageUrl="https://farm66.staticflickr.com/$server/${id}_${secret}.jpg"

       with(holder) {
           binding.tvImage.text=title
           Glide.with(binding.imageView)
               .load(imageUrl)
               .centerCrop()
               .into(binding.imageView)

           binding.imageView.setOnClickListener {
               fragment.findNavController().navigate(R.id.action_homeFragment_to_imageFragment)
               ImageFragment.image= Image(0,title,imageUrl)
           }
        }


    }

    override fun getItemCount()=photos.size
}
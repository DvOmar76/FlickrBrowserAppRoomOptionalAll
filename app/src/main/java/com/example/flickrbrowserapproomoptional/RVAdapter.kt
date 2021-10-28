package com.example.flickrbrowserapproomoptional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.databinding.ItemRowBinding


class RVAdapter(val photos:List<Image>,val activity: MainActivity):RecyclerView.Adapter<RVAdapter.RVHolder>() {
    class RVHolder(view: View):RecyclerView.ViewHolder(view){
        val binding= ItemRowBinding.bind(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {

        val id=photos[position].id
        val title=photos[position].title
        val url=photos[position].url

       with(holder){
            binding.tvImage.text=title
         Glide.with(activity)
            .load(url)
             .centerCrop()
            .into(binding.imageView)
           binding.imageView.setOnClickListener {
                Glide.with(activity)
                    .load(url)
                    .centerCrop()
                    .into(activity.binding.imageViewFullScreen)
                activity.binding.imageViewFullScreen.isVisible=true
                activity.binding.linearLayout.isVisible=false
            }
            activity.binding.imageViewFullScreen.setOnClickListener {
                activity.binding.imageViewFullScreen.isVisible=false
                activity.binding.linearLayout.isVisible=true


            }
        }


    }

    override fun getItemCount()=photos.size
}
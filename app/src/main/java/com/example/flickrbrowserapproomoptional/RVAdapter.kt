package com.example.flickrbrowserapproomoptional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.databinding.ItemRowBinding


class RVAdapter(val photos:List<Image>,fragment: StarFragment):RecyclerView.Adapter<RVAdapter.RVHolder>() {
   val fragment=fragment


    class RVHolder (view: View):RecyclerView.ViewHolder(view){
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

         Glide.with(binding.imageView)
            .load(url)
             .centerCrop()
            .into(binding.imageView)
//           binding.imageView.setOnClickListener {
//            fragment.findNavController().navigate(R.id.action_homeFragment_to_imageFragment)
//               ImageFragment.image=photos[position]
//            }


        }


    }

    override fun getItemCount()=photos.size
}
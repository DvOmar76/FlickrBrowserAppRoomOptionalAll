package com.example.flickrbrowserapproomoptional

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.DBRoom.ImageDatabase
import com.example.flickrbrowserapproomoptional.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

lateinit var binding: FragmentImageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentImageBinding.inflate(inflater,container,false)
        binding.btnAddToFa.setOnClickListener{
            ImageDatabase.getInstance(requireContext()).imageDao().addImage(image)
        }
        Glide.with(binding.imageViewFullScreen)
            .load(image.url)
            .centerCrop()
            .into(binding.imageViewFullScreen)

        return binding.root
    }

    companion object{
        lateinit var image:Image
    }

}
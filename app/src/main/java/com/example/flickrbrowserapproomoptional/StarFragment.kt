package com.example.flickrbrowserapproomoptional

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.DBRoom.ImageDatabase
import com.example.flickrbrowserapproomoptional.databinding.FragmentStarBinding


class StarFragment : Fragment() {
lateinit var binding: FragmentStarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStarBinding.inflate(inflater,container,false)

            updateRV()
        return binding.root
    }
    fun updateRV(){
        val photos= ImageDatabase.getInstance(requireActivity().applicationContext).imageDao().getAll()
        binding.recyclerView.adapter=RVAdapter(photos,this)
        binding.recyclerView.layoutManager= LinearLayoutManager(activity)
    }


}
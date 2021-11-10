package com.example.flickrbrowserapproomoptional

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.DBRoom.ImageDatabase
import com.example.flickrbrowserapproomoptional.databinding.FragmentAddImageBinding


class AddImageFragment : Fragment() {
  lateinit var binding: FragmentAddImageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= FragmentAddImageBinding.inflate(inflater,container,false)

        binding.btnAdd.setOnClickListener {
            val title=binding.edTitle.text.toString()
            val url=binding.edTitle.text.toString()
            ImageDatabase.getInstance(requireContext().applicationContext).imageDao().addImage(Image(0,title,url))
            findNavController().navigate(R.id.action_addImageFragment_to_homeFragment)
        }
        return view
    }
}
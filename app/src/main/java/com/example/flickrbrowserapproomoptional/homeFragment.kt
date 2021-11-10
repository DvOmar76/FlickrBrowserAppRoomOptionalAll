package com.example.flickrbrowserapproomoptional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.DBRoom.ImageDatabase
import com.example.flickrbrowserapproomoptional.Retrofit.APIInterface
import com.example.flickrbrowserapproomoptional.Retrofit.ApiClint
import com.example.flickrbrowserapproomoptional.Retrofit.Flickr
import com.example.flickrbrowserapproomoptional.Retrofit.Photo
import com.example.flickrbrowserapproomoptional.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class homeFragment : Fragment() {
    lateinit var listPhotos: List<Image>
     var listPhotosApi= ArrayList<Photo>()
    lateinit var photos: List<Image>
  lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var tag=""

        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)

//        updateRV()

        binding.btnSearch.setOnClickListener {
            tag = binding.edSearch.text.toString()
            if (tag.isNotEmpty()) {
                if (tag.toString() == "reload") {
//                    updateRV()
                    fetchData(tag)
                    binding.edSearch.text.clear()

                }
                else {
                    fetchData(tag)
                }

            } else {
                Toast.makeText(requireContext().applicationContext, "please enter word", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnStar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_starFragment)
        }
//        view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
//            view.findNavController().navigate(R.id.action_homeFragment_to_imageFragment)
//        }
        binding.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_homeFragment_to_addImageFragment)
        }



        return binding.root
    }
    val apiInterface = ApiClint().getClient()?.create(APIInterface::class.java)

    fun fetchData(tags:String){
        if (apiInterface!=null){
            apiInterface.search(tags)?.enqueue(object : Callback<Flickr?> {
                override fun onResponse(call: Call<Flickr?>, response: Response<Flickr?>) {
                    val photos=response.body()!!.photos.photo
                    for (photo in photos){
                        listPhotosApi.add(photo)
                    }
                    binding.recyclerView.adapter=RVAdapterApi(listPhotosApi,this@homeFragment)
                    binding.recyclerView.layoutManager=LinearLayoutManager(activity)

                }
                override fun onFailure(call: Call<Flickr?>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();

                }
            })
        }
    }

}
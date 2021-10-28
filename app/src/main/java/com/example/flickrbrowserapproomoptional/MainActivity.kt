package com.example.flickrbrowserapproomoptional

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrbrowserapproomoptional.DBRoom.Image
import com.example.flickrbrowserapproomoptional.DBRoom.ImageDatabase
import com.example.flickrbrowserapproomoptional.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var binding: ActivityMainBinding
    var tag=""
   lateinit var listPhotos:List<Image>
   lateinit var photos:List<Image>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateRV()

        binding.btnSearch.setOnClickListener {
            tag=binding.edSearch.text.toString()
            if(tag.isNotEmpty())
            {
                if (tag=="reload")
                {
                    updateRV()
                    binding.edSearch.text.clear()

                }
                else
                {
                    listPhotos= ImageDatabase.getInstance(applicationContext).imageDao().search(tag)
                    binding.recyclerView.adapter=RVAdapter(listPhotos,this@MainActivity)
                    binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.edSearch.text.clear()
                }

            }
            else
            {
                Toast.makeText(applicationContext, "please enter word", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun alert(view: android.view.View) {
        alertDialog()
    }
    fun alertDialog(){
        val emptyFiled=false
        val dialogBuild= AlertDialog.Builder(this)
        val lLayout= LinearLayout(this)
        val edTitle= EditText(this)
        val edUrl= EditText(this)
        dialogBuild.setTitle("Add image")
        edTitle.setSingleLine()
        edUrl.setSingleLine()
        edTitle.setHint("enter Title")
        edUrl.setHint("enter Url")
        lLayout.addView(edTitle)
        lLayout.addView(edUrl)
        lLayout.setPadding(50,40,50,10)

        dialogBuild.setNegativeButton("cancel", null)
        dialogBuild.setPositiveButton("add", DialogInterface.OnClickListener { _, _ ->
            val title=edTitle.text.toString()
            val url=edUrl.text.toString()
            if(title.isNotEmpty()&&url.isNotEmpty()){
                val image=Image(0,title,url)
                ImageDatabase.getInstance(applicationContext).imageDao().addImage(image)
                updateRV()

            }
            else
            {
                Toast.makeText(applicationContext, "pleas complete fields", Toast.LENGTH_SHORT).show()
            }
        })
        val aler=dialogBuild.create()
        aler.setView(lLayout)
        aler.show()
    }
    fun updateRV(){
             photos=ImageDatabase.getInstance(applicationContext).imageDao().getAll()
            binding.recyclerView.adapter=RVAdapter(photos,this@MainActivity)
            binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
    }

}
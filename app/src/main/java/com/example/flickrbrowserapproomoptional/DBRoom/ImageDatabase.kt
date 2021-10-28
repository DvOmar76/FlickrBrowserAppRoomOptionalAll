package com.example.flickrbrowserapproomoptional.DBRoom

import android.content.Context
import android.text.Editable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Image::class],version = 1,exportSchema = false)
abstract class ImageDatabase :RoomDatabase(){

    companion object{
        var instance:ImageDatabase?=null
        fun getInstance(context: Context):ImageDatabase
        {
            if(instance!=null)
            {
                return instance as ImageDatabase
            }
            instance= Room.databaseBuilder(context,ImageDatabase::class.java,"Images").run { allowMainThreadQueries()}.build()
            return instance as ImageDatabase
        }
    }
    abstract fun imageDao():ImageDao

}
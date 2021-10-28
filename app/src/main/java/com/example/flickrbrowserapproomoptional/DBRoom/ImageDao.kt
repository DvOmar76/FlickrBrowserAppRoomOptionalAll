package com.example.flickrbrowserapproomoptional.DBRoom
import androidx.room.*
@Dao
interface ImageDao {
    @Query("select * from Image order by id asc")
    fun getAll():List<Image>
    @Insert
    fun addImage(image: Image)
    @Query("select * from Image where title like :search ")
    fun search(search :String):List<Image>
}
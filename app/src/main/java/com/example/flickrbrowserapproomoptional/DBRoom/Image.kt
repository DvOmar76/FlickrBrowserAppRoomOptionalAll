package com.example.flickrbrowserapproomoptional.DBRoom
import androidx.room.*
@Entity(tableName = "Image")
data class Image(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int=0,
  @ColumnInfo(name = "title") val title:String,
   @ColumnInfo(name = "url")val url:String
)

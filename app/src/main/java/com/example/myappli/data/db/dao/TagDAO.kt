package com.example.myappli.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myappli.model.Category
import com.example.myappli.model.Tag

@Dao
interface TagDAO : BaseDAO<Tag> {


    @Query("select * from tag where  category = :category ")
    suspend fun getTag(category: String): Tag?


}

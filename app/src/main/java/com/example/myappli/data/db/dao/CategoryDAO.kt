package com.example.myappli.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myappli.data.db.dao.BaseDAO
import com.example.myappli.model.Category


@Dao
interface CategoryDAO : BaseDAO<Category> {

    @Query("select * from category where id!=:id and parent!=:p")
    suspend fun getAllCategories(id: Int = 1, p:Int = 0): List<Category>

    @Query("select * from category where parent=:id")
    suspend fun getCategories(id: Int): List<Category>


}
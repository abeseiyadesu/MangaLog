package com.example.completereadbookapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ComicDao {
    @Insert
    suspend fun insert(comic: Comic)

    @Query("SELECT * FROM Comic")
    fun getAllComics(): LiveData<List<Comic>>

    @Query("SELECT * FROM Comic WHERE id = :id")
    suspend fun getComicById(id: Int): Comic?

    @Update
    suspend fun update(comic: Comic)

    @Delete
    suspend fun delete(comic: Comic)
}
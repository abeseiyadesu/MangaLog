package com.example.completereadbookapp.data

import androidx.lifecycle.LiveData

class ComicRepository(private val comicDao: ComicDao) {
    fun getAllComics(): LiveData<List<Comic>> = comicDao.getAllComics()

    suspend fun insert(comic: Comic) = comicDao.insert(comic)
    suspend fun update(comic: Comic) = comicDao.update(comic)
    suspend fun delete(comic: Comic) = comicDao.delete(comic)

    suspend fun getComicById(id: Int): Comic? {
        return comicDao.getComicById(id)
    }
}
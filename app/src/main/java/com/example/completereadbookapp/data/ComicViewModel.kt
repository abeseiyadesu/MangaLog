package com.example.completereadbookapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.Collator
import java.util.Locale

// applicationクラス を 利用したいため AndroidViewModelを使用
class ComicViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ComicRepository
    val allComics: LiveData<List<Comic>>

    private val _isAscending = MutableLiveData(true)

    // データベースのデータを取得
    private val _allComics: LiveData<List<Comic>>

    // ソートしたデータを取得
    val sortedComics: LiveData<List<Comic>>

    init {
        val comicDatabase = ComicDatabase.getDatabase(application)
        repository = ComicRepository(comicDatabase.comicDao())

        // データベースから全データ取得
        _allComics = repository.getAllComics()

        // 日本語対応のソート用 Collator
        val collator = Collator.getInstance(Locale.JAPANESE)
        collator.strength = Collator.PRIMARY // ひらがな・カタカナ・漢字を区別せずソート

        // ソート状態が変わるたびに更新
        sortedComics = _isAscending.switchMap { ascending ->
            _allComics.map { comics ->
                if (ascending) {
                    comics.sortedWith(compareBy(collator) { it.title }) // 日本語対応ソート
                } else {
                    comics.sortedBy { it.id } // ID順（追加順）
                }
            }
        }

        allComics = sortedComics // `allComics` は `sortedComics` を参照
    }

    fun insertComic(comic: Comic) = viewModelScope.launch {
        repository.insert(comic)
    }

    fun updateComic(comic: Comic) = viewModelScope.launch {
        repository.update(comic)
    }

    fun deleteComic(comicId: Int) = viewModelScope.launch {
        val comic = repository.getComicById(comicId)
        if (comic != null) {
            repository.delete(comic)
        }
    }

    fun getComicById(id: Int): LiveData<Comic?> {
        val comicLiveData = MutableLiveData<Comic?>()
        viewModelScope.launch {
            val comic = repository.getComicById(id)
            comicLiveData.postValue(comic)
        }
        return comicLiveData
    }
}

package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.ResultRepository
import com.dicoding.asclepius.data.ResultRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val mResultRepository: ResultRepository

    private val _listResult = MutableLiveData<List<Result>>()
    var listResult: LiveData<List<Result>> = _listResult

    init {
        val resultDao = ResultRoomDatabase.getInstance(application).resultDao()
        mResultRepository = ResultRepository(resultDao)
        listResult = mResultRepository.getAllData()
    }

    private fun getResults() {
        viewModelScope.launch(Dispatchers.IO) {
            mResultRepository.getAllData()
        }
    }

    fun insert(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            mResultRepository.insert(result)
        }
    }

}
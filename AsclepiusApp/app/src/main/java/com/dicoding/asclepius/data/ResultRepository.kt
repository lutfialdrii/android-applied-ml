package com.dicoding.asclepius.data

import androidx.lifecycle.LiveData

class ResultRepository(private val mResultDao: ResultDao) {

    fun getAllData(): LiveData<List<Result>> = mResultDao.getAll()

    fun insert(result: Result) {
        mResultDao.insert(result)
    }

}
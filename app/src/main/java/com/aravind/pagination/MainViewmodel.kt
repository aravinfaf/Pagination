package com.aravind.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewmodel : ViewModel() {

    lateinit var retrofitService: RetrofitService

    init {
        retrofitService = RetroInstance.getRetroInstance().create(RetrofitService::class.java)
    }

    fun getListData() : Flow<PagingData<CharacterData>>{
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = {CharacterPaginSource(retrofitService)}).flow.cachedIn(
            viewModelScope
        )
    }
}
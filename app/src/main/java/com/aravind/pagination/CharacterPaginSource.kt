package com.aravind.pagination

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CharacterPaginSource(private val apiService: RetrofitService) :
    PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE
            val response = apiService.getDataFromAPI(nextPage)
            var nextPageNumber: Int? = null
            if (response?.info?.next != null) {
                val uri = Uri.parse(response?.info?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}
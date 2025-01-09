package com.example.myapplication.features.locomotives.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.features.locomotives.models.Locomotive
import retrofit2.Call

interface LocomotiveRepository {
    suspend fun getLocomotive(id: Int): Locomotive
    fun updateLocomotive(id: Int, train: Locomotive): Call<Locomotive>
    fun deleteLocomotive(id:Int): Call<Locomotive>
    fun createLocomotive(locomotive: Locomotive): Call<Locomotive>
}

class LocomotiveRepositoryImpl(private val service: LocomotiveService, private val name: String): LocomotiveRepository, PagingSource<Int, Locomotive>() {
    override suspend fun getLocomotive(id: Int): Locomotive {
        val body = service.locomotive(id).body()!!
        return body.results.orEmpty()[0]
    }

    override fun updateLocomotive(id: Int, train: Locomotive) : Call<Locomotive> {
        return service.updateLocomotive(id, train)
    }

    override fun deleteLocomotive(id: Int): Call<Locomotive> {
        return service.deleteLocomotive(id)
    }

    override fun createLocomotive(locomotive: Locomotive): Call<Locomotive> {
        return service.createLocomotive(locomotive)
    }

    override fun getRefreshKey(state: PagingState<Int, Locomotive>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Locomotive> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.locomotives(page = currentPage, pageSize = params.loadSize, name = name)
            LoadResult.Page(
                data = response.body()!!.results.orEmpty(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.body()!!.results?.isEmpty() == true) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
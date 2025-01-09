package com.example.myapplication.features.wagons.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.features.wagons.models.Wagon
import retrofit2.Call

interface WagonRepository {
    suspend fun getWagon(id: Int): Wagon
    fun updateWagon(id: Int, train: Wagon): Call<Wagon>
    fun deleteWagon(id:Int): Call<Wagon>
    fun createWagon(wagon: Wagon): Call<Wagon>
}

class WagonRepositoryImpl(private val service: WagonService, private val name: String): WagonRepository, PagingSource<Int, Wagon>() {
    override suspend fun getWagon(id: Int): Wagon {
        val body = service.wagon(id).body()!!
        return body.results.orEmpty()[0]
    }

    override fun updateWagon(id: Int, train: Wagon) : Call<Wagon> {
        return service.updateWagon(id, train)
    }

    override fun deleteWagon(id: Int): Call<Wagon> {
        return service.deleteWagon(id)
    }

    override fun createWagon(wagon: Wagon): Call<Wagon> {
        return service.createWagon(wagon)
    }

    override fun getRefreshKey(state: PagingState<Int, Wagon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wagon> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.wagons(page = currentPage, pageSize = params.loadSize, name = name)
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
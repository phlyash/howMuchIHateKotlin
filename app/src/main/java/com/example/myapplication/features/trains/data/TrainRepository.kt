package com.example.myapplication.features.trains.data

import com.example.myapplication.features.trains.models.Train
import retrofit2.Call
import androidx.paging.PagingSource
import androidx.paging.PagingState


interface TrainRepository {
    suspend fun getTrain(id: Int): Train
    fun updateTrain(id: Int, train: Train): Call<Train>
    fun deleteTrain(id:Int): Call<Train>
    fun createTrain(train: Train): Call<Train>
}

class TrainRepositoryImpl(private val service: TrainService, private val name: String): TrainRepository, PagingSource<Int, Train>() {
    override suspend fun getTrain(id: Int): Train {
        val body = service.train(id).body()!!
        return body.results.orEmpty()[0]
    }

    override fun updateTrain(id: Int, train: Train) : Call<Train> {
        return service.updateTrain(id, train)
    }

    override fun deleteTrain(id: Int): Call<Train> {
        return service.deleteTrain(id)
    }

    override fun createTrain(train: Train) : Call<Train> {
        return service.createTrain(train)
    }

    override fun getRefreshKey(state: PagingState<Int, Train>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Train> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.trains(page = currentPage, pageSize = params.loadSize, name = name)
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
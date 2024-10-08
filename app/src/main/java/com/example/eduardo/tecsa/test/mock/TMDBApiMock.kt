package com.example.eduardo.tecsa.test.mock

import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.lib.api.TMDBApi
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse

val dummyData = listOf(
    TMDBMovie(1,"title1", "overview1","releaseData1","posterPath1",5.0),
    TMDBMovie(2,"title2", "overview2","releaseData2","posterPath2",6.0),
    TMDBMovie(3,"title3", "overview3","releaseData3","posterPath3",6.5),
    TMDBMovie(11,"title11", "overview11","releaseData11","posterPath11",5.0),
    TMDBMovie(22,"title22", "overview22","releaseData22","posterPath22",6.0),
    TMDBMovie(33,"title33", "overview33","releaseData33","posterPath33",6.5),
)

class TMDBApiMock: TMDBApi {
    companion object {
        var FORCE_ERROR = false
    }

    override suspend fun getTrendingMovieList(page: Int): TrendingResponse {
        if(FORCE_ERROR) throw Exception()
        return when(page) {
            1 -> TrendingResponse(results = dummyData.subList(0,3), total_pages = 2)
            else -> TrendingResponse(results = dummyData.subList(3,7), total_pages = 2)
        }
    }
}
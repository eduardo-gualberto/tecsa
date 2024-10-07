package com.example.eduardo.tecsa.lib.api.responses

import com.example.eduardo.tecsa.domain.model.TMDBMovie

data class TrendingResponse(val results: List<TMDBMovie>, val total_pages: Int)

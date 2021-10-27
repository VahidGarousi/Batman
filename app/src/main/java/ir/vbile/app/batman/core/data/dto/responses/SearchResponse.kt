package ir.vbile.app.batman.core.data.dto.responses

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val movies: List<MovieDto>,
    @SerializedName("total_results")
    val totalResults: String
)
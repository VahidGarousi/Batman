package ir.vbile.app.batman.core.domain.models

data class SearchResponse(
    val response: String,
    val search: List<Search>,
    val totalResults: String
)
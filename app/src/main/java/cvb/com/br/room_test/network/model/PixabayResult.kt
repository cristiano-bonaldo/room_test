package cvb.com.br.room_test.network.model

data class PixabayResult(
    val hits: List<PixabayHit>,
    val total: Int,
    val totalHits: Int
)
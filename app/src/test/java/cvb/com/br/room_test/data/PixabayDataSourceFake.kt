package cvb.com.br.room_test.data

import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.network.model.PixabayHit
import cvb.com.br.room_test.network.model.PixabayResult

class PixabayDataSourceFake(var isError: Boolean = false) : PixabayDataSource {

    override suspend fun get(): PixabayResult {
        if (isError) {
            throw Throwable("API ERROR")
        }
        else {
            val hits = listOf<PixabayHit>()
            return PixabayResult(hits, 0,0)
        }
    }
}
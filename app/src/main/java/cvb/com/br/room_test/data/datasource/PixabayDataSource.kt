package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.network.model.PixabayResult

interface PixabayDataSource {
    suspend fun get(): PixabayResult
}
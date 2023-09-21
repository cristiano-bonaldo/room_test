package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.network.model.PixabayResult

class PixabayRepository (private val dataSource: PixabayDataSource) {

    suspend fun get(): PixabayResult {
        return dataSource.get()
    }
}
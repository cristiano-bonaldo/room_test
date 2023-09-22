package cvb.com.br.room_test.data.remote

import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.network.service.ApiService
import cvb.com.br.room_test.network.model.PixabayResult
import cvb.com.br.room_test.network.util.ApiHandleDataResult
import javax.inject.Inject

class RemotePixabayDataSource @Inject constructor(private val apiInterface: ApiService) : PixabayDataSource {

    override suspend fun get(): PixabayResult {
        return ApiHandleDataResult.handleData { apiInterface.search("flower") }
    }

}
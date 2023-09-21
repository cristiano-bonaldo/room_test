package cvb.com.br.room_test.data.remote

import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.network.`interface`.ApiInterface
import cvb.com.br.room_test.network.model.PixabayResult
import cvb.com.br.room_test.network.util.ApiHandleDataResult

class RemotePixabayDataSource (private val apiInterface: ApiInterface) : PixabayDataSource {

    override suspend fun get(): PixabayResult {
        return ApiHandleDataResult.handleData { apiInterface.search("flower") }
    }

}
package cvb.com.br.room_test.network.`interface`

import cvb.com.br.room_test.network.model.PixabayResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayInterface {

    @GET("api/")
    suspend fun search(@Query("q") information: String): Response<PixabayResult>

}
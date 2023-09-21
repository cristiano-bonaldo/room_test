package cvb.com.br.room_test.data

import cvb.com.br.room_test.data.datasource.UserModuleJoinDataSource
import cvb.com.br.room_test.db.data.UserModuleJoin

class UserModuleJoinDataSourceFake : UserModuleJoinDataSource {
    override suspend fun getList(): List<UserModuleJoin> {
        TODO("Not yet implemented")
    }
}
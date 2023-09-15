package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.db.data.UserModuleJoin

interface UserModuleJoinDataSource {

    suspend fun getList(): List<UserModuleJoin>

}
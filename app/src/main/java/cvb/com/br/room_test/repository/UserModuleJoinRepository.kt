package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.UserModuleJoinDataSource
import cvb.com.br.room_test.db.data.UserModuleJoin
import cvb.com.br.room_test.di.UserModuleJoinDataSourceLocal
import javax.inject.Inject

class UserModuleJoinRepository @Inject constructor(@UserModuleJoinDataSourceLocal private val dataSource: UserModuleJoinDataSource) {

    suspend fun getList(): List<UserModuleJoin> {
        return dataSource.getList()
    }

}
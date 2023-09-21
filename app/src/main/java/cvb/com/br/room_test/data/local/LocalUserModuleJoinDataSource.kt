package cvb.com.br.room_test.data.local

import cvb.com.br.room_test.data.datasource.UserModuleJoinDataSource
import cvb.com.br.room_test.db.dao.UserModuleJoinDao
import cvb.com.br.room_test.db.data.UserModuleJoin
import javax.inject.Inject

class LocalUserModuleJoinDataSource @Inject constructor(private val userModuleJoinDao: UserModuleJoinDao) : UserModuleJoinDataSource {

    override suspend fun getList(): List<UserModuleJoin> {
        return userModuleJoinDao.getList()
    }
}
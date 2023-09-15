package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.data.datasource.UserModuleJoinDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.data.UserDepartmentJoin
import cvb.com.br.room_test.db.data.UserModuleJoin

class LocalUserModuleJoinDataSource(context: Context) : UserModuleJoinDataSource {

    private val userModuleJoinDao = AppDataBase.getDatabase(context).userModuleJoinDao()

    override suspend fun getList(): List<UserModuleJoin> {
        return userModuleJoinDao.getList()
    }
}
package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.data.UserDepartmentJoin

class LocalUserDepartmentJoinDataSource(context: Context) : UserDepartmentJoinDataSource {

    private val userDepartmentJoinDao = AppDataBase.getDatabase(context).userDepartmentJoinDao()

    override suspend fun getList(): List<UserDepartmentJoin> {
        return userDepartmentJoinDao.getList()
    }
}
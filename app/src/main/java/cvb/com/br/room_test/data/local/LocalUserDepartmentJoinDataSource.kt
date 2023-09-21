package cvb.com.br.room_test.data.local

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.dao.UserDepartmentJoinDao
import cvb.com.br.room_test.db.data.UserDepartmentJoin
import javax.inject.Inject

class LocalUserDepartmentJoinDataSource @Inject constructor(private val userDepartmentJoinDao: UserDepartmentJoinDao) : UserDepartmentJoinDataSource {

    override suspend fun getList(): List<UserDepartmentJoin> {
        return userDepartmentJoinDao.getList()
    }
}
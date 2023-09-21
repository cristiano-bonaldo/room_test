package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.dao.UserModuleDao
import cvb.com.br.room_test.db.entity.UserModule
import javax.inject.Inject

class LocalUserModuleDataSource @Inject constructor(private val userModuleDao: UserModuleDao) : UserModuleDataSource {

    override suspend fun getList(): List<UserModule> {
        return userModuleDao.getList()
    }
}
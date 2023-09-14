package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.entity.UserModule

class LocalUserModuleDataSource(context: Context) : UserModuleDataSource {

    private val userModuleDao = AppDataBase.getDatabase(context).userModuleDao()

    override suspend fun getList(): List<UserModule> {
        return userModuleDao.getList()
    }
}
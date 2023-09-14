package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.entity.User

class LocalUserDataSource(context: Context) : UserDataSource {

    private val userDao = AppDataBase.getDatabase(context).userDao()

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun getList(): List<User> {
        return userDao.getList()
    }
}
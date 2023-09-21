package cvb.com.br.room_test.data.local

import androidx.lifecycle.LiveData
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.entity.User
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(private val userDao: UserDao) : UserDataSource {

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override suspend fun getUser(userId: Long): User {
        return userDao.getUser(userId)
    }

    override suspend fun getList(): List<User> {
        return userDao.getList()
    }

    override fun getObservableList(): LiveData<List<User>> = userDao.getObservableList()
}
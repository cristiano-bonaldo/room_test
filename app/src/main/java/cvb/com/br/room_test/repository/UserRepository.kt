package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.UserDataSource
import cvb.com.br.room_test.db.entity.User

class UserRepository(private val dataSource: UserDataSource) {

    suspend fun getList(): List<User> {
        return dataSource.getList()
    }

    suspend fun insert(user: User) {
        dataSource.insert(user)
    }

}
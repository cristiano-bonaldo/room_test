package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.di.UserDataSourceLocal
import cvb.com.br.room_test.di.UserModuleDataSourceLocal
import javax.inject.Inject

class UserRepository @Inject constructor(@UserDataSourceLocal private val dataSource: UserDataSource) {

    suspend fun getList(): List<User> {
        return dataSource.getList()
    }

    suspend fun insert(user: User) {
        dataSource.insert(user)
    }

}
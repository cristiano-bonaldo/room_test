package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.db.entity.UserModule
import cvb.com.br.room_test.di.UserModuleDataSourceLocal
import javax.inject.Inject

class UserModuleRepository @Inject constructor(@UserModuleDataSourceLocal private val dataSource: UserModuleDataSource) {

    suspend fun getList(): List<UserModule> {
        return dataSource.getList()
    }
}
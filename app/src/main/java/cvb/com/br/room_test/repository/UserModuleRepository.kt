package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.db.entity.UserModule

class UserModuleRepository(private val dataSource: UserModuleDataSource) {

    suspend fun getList(): List<UserModule> {
        return dataSource.getList()
    }
}
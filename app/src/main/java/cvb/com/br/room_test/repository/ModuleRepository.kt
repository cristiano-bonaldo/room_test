package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.di.ModuleDataSourceLocal
import cvb.com.br.room_test.di.UserDataSourceLocal
import javax.inject.Inject

class ModuleRepository @Inject constructor(@ModuleDataSourceLocal private val dataSource: ModuleDataSource) {

    suspend fun getList(): List<Module> {
        return dataSource.getList()
    }
}
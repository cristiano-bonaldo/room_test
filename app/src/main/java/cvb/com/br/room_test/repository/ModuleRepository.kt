package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.db.entity.Module

class ModuleRepository(private val dataSource: ModuleDataSource) {

    suspend fun getList(): List<Module> {
        return dataSource.getList()
    }
}
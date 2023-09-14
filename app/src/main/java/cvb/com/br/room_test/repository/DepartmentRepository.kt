package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.User

class DepartmentRepository(private val dataSource: DepartmentDataSource) {

    suspend fun getList(): List<Department> {
        return dataSource.getList()
    }
}
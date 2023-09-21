package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.di.DepartmentDataSourceLocal
import javax.inject.Inject

class DepartmentRepository @Inject constructor(@DepartmentDataSourceLocal private val dataSource: DepartmentDataSource) {

    suspend fun getList(): List<Department> {
        return dataSource.getList()
    }
}
package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.dao.DepartmentDao
import cvb.com.br.room_test.db.entity.Department
import javax.inject.Inject

class LocalDepartmentDataSource @Inject constructor (private val departmentDao: DepartmentDao) : DepartmentDataSource {

    override suspend fun getList(): List<Department> {
        return departmentDao.getList()
    }
}
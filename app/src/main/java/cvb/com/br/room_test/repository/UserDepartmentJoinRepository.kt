package cvb.com.br.room_test.repository

import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.db.data.UserDepartmentJoin
import cvb.com.br.room_test.di.UserDepartmentJoinDataSourceLocal
import javax.inject.Inject

class UserDepartmentJoinRepository @Inject constructor(@UserDepartmentJoinDataSourceLocal private val dataSource: UserDepartmentJoinDataSource) {
    suspend fun getList(): List<UserDepartmentJoin> {
        return dataSource.getList()
    }

}
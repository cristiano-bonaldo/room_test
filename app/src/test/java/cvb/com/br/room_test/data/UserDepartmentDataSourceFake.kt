package cvb.com.br.room_test.data

import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.db.data.UserDepartmentJoin

class UserDepartmentJoinDataSourceFake : UserDepartmentJoinDataSource {
    override suspend fun getList(): List<UserDepartmentJoin> {
        TODO("Not yet implemented")
    }
}
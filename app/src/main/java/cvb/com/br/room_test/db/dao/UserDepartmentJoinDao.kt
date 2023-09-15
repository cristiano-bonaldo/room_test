package cvb.com.br.room_test.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import cvb.com.br.room_test.db.data.UserDepartmentJoin

@Dao
interface UserDepartmentJoinDao {
    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getList(): List<UserDepartmentJoin>
}
package cvb.com.br.room_test.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.entity.User

@Dao
interface ModuleDao {
    @Query("SELECT * FROM module ORDER BY name ASC")
    suspend fun getList(): List<Module>
}
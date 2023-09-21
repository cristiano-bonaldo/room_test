package cvb.com.br.room_test.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cvb.com.br.room_test.db.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUser(userId: Long): User

    @Query("SELECT * FROM user ORDER BY created_at ASC")
    suspend fun getList(): List<User>

    @Query("SELECT * FROM user ORDER BY created_at ASC")
    fun getObservableList(): LiveData<List<User>>
}
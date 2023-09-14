package cvb.com.br.room_test.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department")
data class Department(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val name: String = "",
)

package cvb.com.br.room_test.db.data

import androidx.room.Embedded
import androidx.room.Relation
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.User

data class UserDepartmentJoin(
    @Embedded val user: User,

    @Relation(
        parentColumn = "department_id",
        entityColumn = "id"
    )
    val department: Department
)
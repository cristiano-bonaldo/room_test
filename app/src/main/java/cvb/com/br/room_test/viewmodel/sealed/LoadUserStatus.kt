package cvb.com.br.room_test.viewmodel.sealed

import cvb.com.br.room_test.db.entity.User

sealed class LoadUserStatus {
    data object Loading : LoadUserStatus()
    class Error(val error: Throwable) : LoadUserStatus()
    class Success(val userList: List<User>) : LoadUserStatus()
}

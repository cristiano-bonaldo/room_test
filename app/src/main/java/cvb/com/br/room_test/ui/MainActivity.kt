package cvb.com.br.room_test.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cvb.com.br.room_test.R
import cvb.com.br.room_test.data.LocalUserDataSource
import cvb.com.br.room_test.repository.UserRepository
import cvb.com.br.room_test.viewmodel.MainViewModel
import cvb.com.br.room_test.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val userDataSource = LocalUserDataSource(applicationContext)
        val userRepository = UserRepository(userDataSource)
        MainViewModelFactory(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.userList.observe(this) { userList ->
            userList.forEach { user -> Log.i("CVB", user.toString()) }
        }

        viewModel.loadUser()
    }
}
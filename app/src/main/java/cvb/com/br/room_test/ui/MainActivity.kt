package cvb.com.br.room_test.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cvb.com.br.room_test.R
import cvb.com.br.room_test.databinding.ActivityMainBinding
import cvb.com.br.room_test.ui.adapter.UserAdapter
import cvb.com.br.room_test.viewmodel.MainViewModel
import cvb.com.br.room_test.viewmodel.sealed.LoadUserStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configUI()

        configObserver()

        configListener()

        viewModel.loadData()
    }

    private fun configUI() {
        adapter = UserAdapter()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }

    private fun configListener() {
        binding.btLoadUser.setOnClickListener {
            viewModel.loadUser()
        }

        binding.btPixabay.setOnClickListener {
            val intent = Intent(this, PixabayActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configObserver() {
        viewModel.moduleList.observe(this) { moduleList ->
            moduleList.forEach { module -> Log.i("CVB", module.toString()) }
        }

        viewModel.departmentList.observe(this) { departmentList ->
            departmentList.forEach { department -> Log.i("CVB", department.toString()) }
        }

        viewModel.userList.observe(this) { userList ->
            userList.forEach { user -> Log.i("CVB", user.toString()) }
        }

        viewModel.userModuleList.observe(this) { userModuleList ->
            userModuleList.forEach { userModule -> Log.i("CVB", userModule.toString()) }
        }

        viewModel.userDepartmentJoinList.observe(this) { userDepartmentJoinList ->
            userDepartmentJoinList.forEach { userDepartmentJoin -> Log.i("CVB", userDepartmentJoin.toString()) }
        }

        viewModel.userModuleJoinList.observe(this) { userModuleJoinList ->
            userModuleJoinList.forEach { userModuleJoin -> Log.i("CVB", userModuleJoin.toString()) }
        }

        // ======

        viewModel.loadUserStatus.observe(this, this::onLoadUserStatus)
    }

    private fun onLoadUserStatus(loadUserStatus: LoadUserStatus) {
        when (loadUserStatus) {
            is LoadUserStatus.Loading -> {
                Toast.makeText(this, "Loading User List...", Toast.LENGTH_SHORT).show()
            }

            is LoadUserStatus.Error -> {
                Toast.makeText(this, "Error: ${loadUserStatus.error}", Toast.LENGTH_SHORT).show()
            }

            is LoadUserStatus.Success -> {
                val userList = loadUserStatus.userList
                adapter.submitList(userList)
            }
        }
    }
}
package cvb.com.br.room_test.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.data.remote.RemotePixabayDataSource
import cvb.com.br.room_test.databinding.ActivityPixabayBinding
import cvb.com.br.room_test.network.RetrofitClient
import cvb.com.br.room_test.repository.PixabayRepository
import cvb.com.br.room_test.viewmodel.PixabayViewModel
import cvb.com.br.room_test.viewmodel.PixabayViewModelFactory
import cvb.com.br.room_test.viewmodel.sealed.LoadPixabayStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayActivity : AppCompatActivity() {

    private val viewModel by viewModels<PixabayViewModel>()

    private lateinit var binding: ActivityPixabayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixabayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configObserver()

        configListener()
    }

    private fun configListener() {
        binding.btLoad.setOnClickListener {
            viewModel.loadPixabay()
        }
    }

    private fun configObserver() {
        viewModel.loadPixabayStatus.observe(this, this::onLoadPixabayStatus)
    }

    private fun onLoadPixabayStatus(loadPixabayStatus: LoadPixabayStatus) {
        when (loadPixabayStatus) {
            is LoadPixabayStatus.Loading -> {
                Toast.makeText(this, "Loading User List...", Toast.LENGTH_SHORT).show()
            }

            is LoadPixabayStatus.Error -> {
                Toast.makeText(this, "Error: ${loadPixabayStatus.error}", Toast.LENGTH_SHORT).show()
            }

            is LoadPixabayStatus.Success -> {
                val pixabayResult = loadPixabayStatus.pixabayResult
                Log.i("CVB", "Pixabay Total = ${pixabayResult.total} - 1º ${pixabayResult.hits[1]}")
            }
        }
    }
}
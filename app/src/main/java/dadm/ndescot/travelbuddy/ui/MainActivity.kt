package dadm.ndescot.travelbuddy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dadm.ndescot.travelbuddy.R
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var localUserDataRepository: LocalUserDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainHostContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }


    override fun onDestroy() {
        super.onDestroy()

        // Delete user id
        lifecycleScope.launch {
            localUserDataRepository.deleteUserId()
        }
    }

}
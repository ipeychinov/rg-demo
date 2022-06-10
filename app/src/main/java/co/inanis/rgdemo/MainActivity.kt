package co.inanis.rgdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import cafe.adriel.broker.GlobalBroker
import cafe.adriel.broker.subscribe
import co.inanis.rgdemo.databinding.ActivityMainBinding
import co.inanis.rgdemo.utils.ErrorEvent

class MainActivity : AppCompatActivity(), GlobalBroker.Subscriber {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navMenu.setupWithNavController(findNavController(R.id.navHost))

        assignObservers()
    }

    private fun assignObservers() {
        subscribe<ErrorEvent>(lifecycleScope) {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }

}
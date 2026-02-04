package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupViewInsets()
        setupNavHost()
        setupAppBar()
        setupBottomNavigation()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (isTopLevelDestination.not()) {
                toolbarApp.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }

    private fun setupViewInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupAppBar() = with(binding.bottomNavMain) {
        setupWithNavController(navController)
    }

    private fun setupBottomNavigation() = with(binding.toolbarApp) {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersFragment,
                R.id.favoritesFragment,
                R.id.aboutFragment
            )
        )
        setupWithNavController(navController, appBarConfiguration)
    }
}

package com.homecookinghelper.homecooked.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.homecookinghelper.homecooked.R
import com.homecookinghelper.homecooked.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= findNavController(R.id.navHostFrag)
        val appBarConfiguration= AppBarConfiguration(setOf(
            R.id.recipeFrag,
            R.id.favourite,
            R.id.foodFact
        ))

        binding.bottomNavigation.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)



    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()||navController.navigateUp()
    }
}
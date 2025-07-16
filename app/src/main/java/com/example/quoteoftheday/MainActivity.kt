package com.example.quoteoftheday

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quoteoftheday.database.QuoteDatabaseHelper
import com.example.quoteoftheday.databinding.ActivityMainBinding
import com.example.quoteoftheday.fragments.FavoritesFragment
import com.example.quoteoftheday.fragments.SettingsFragment
import com.example.quoteoftheday.fragments.TodayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the default fragment
        replaceFragment(TodayFragment())

        // Handle navigation item clicks
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_today -> replaceFragment(TodayFragment())
                R.id.nav_favorites -> replaceFragment(FavoritesFragment())
                R.id.nav_settings -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clear_favorites -> {
                val db = QuoteDatabaseHelper(this)
                db.clearAllQuotes()
                Toast.makeText(this, "Favorites cleared!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_about -> {
                Toast.makeText(this, "Quote of the Day App\nMade for Uni 😊", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

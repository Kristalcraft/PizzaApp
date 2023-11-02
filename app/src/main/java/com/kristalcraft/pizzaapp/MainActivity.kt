package com.kristalcraft.pizzaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kristalcraft.pizzaapp.databinding.ActivityMainBinding
import com.kristalcraft.pizzaapp.ui.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openMenu()
    }

    fun openMenu(){
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MenuFragment(), MENU)
            .commit()
    }

    companion object{
        const val MENU = "menu"
    }
}
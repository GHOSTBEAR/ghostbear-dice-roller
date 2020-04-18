package me.ghostbear.dices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.ghostbear.dices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, DiceFragment.newInstance(20))
            .addToBackStack(null)
            .commit()
    }
}

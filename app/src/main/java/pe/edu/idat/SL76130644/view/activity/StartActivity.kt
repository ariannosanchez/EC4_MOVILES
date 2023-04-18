package pe.edu.idat.SL76130644.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.idat.SL76130644.R
import pe.edu.idat.SL76130644.databinding.ActivityStartBinding
import pe.edu.idat.SL76130644.view.fragment.*

class StartActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStartBinding
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_navigation, HomeFragment()).commit()
        }
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bnvNavigation.setOnItemSelectedListener { menuItem ->
            val fragment: Fragment = when (menuItem.itemId){
                R.id.nav_home ->{
                    HomeFragment()
                }
                R.id.nav_password ->{
                    PasswordFragment()
                }
                R.id.nav_list ->{
                    ListFragment()
                }
                R.id.nav_info ->{
                    InfoFragment()
                }
                R.id.nav_logout ->{
                    LogoutFragment()
                }
                else ->{
                    HomeFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fl_navigation, fragment).commit()
            return@setOnItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            Toast.makeText(this, "Saliste de la aplicación", Toast.LENGTH_SHORT).show()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Presione 2 veces para salir", Toast.LENGTH_SHORT).show()

        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                doubleBackToExitPressedOnce = false
            }
        }.start()
    }
}
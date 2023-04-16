package pe.edu.idat.SL76130644.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.SL76130644.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        binding.btnloginRe.setOnClickListener {
            goLoginActivity()
        }

        binding.btnsignupRe.setOnClickListener {
            val email = binding.etEmailRe.text.toString()
            val password = binding.etPasswordRe.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {result ->
                        if (result.isSuccessful){
                            showSuccessMessageAndGoToLogin()
                        }else{
                            showAlertError()
                        }
                    }
            }else{
                showAlertError()
            }
        }
    }

    private fun showAlertError(){
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Ups, ocurrio un error")
        alertBuilder.setMessage("Se produjo un error en su registro")
        alertBuilder.setPositiveButton("Aceptar", null)
        val alert: AlertDialog = alertBuilder.create()
        alert.show()
    }

    private fun showSuccessMessageAndGoToLogin() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Registro exitoso")
        alertBuilder.setMessage("Su cuenta ha sido creada exitosamente")
        alertBuilder.setPositiveButton("Aceptar") { _, _ ->
            goLoginActivity()
        }
        alertBuilder.setCancelable(false)
        val alert: AlertDialog = alertBuilder.create()
        alert.show()
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
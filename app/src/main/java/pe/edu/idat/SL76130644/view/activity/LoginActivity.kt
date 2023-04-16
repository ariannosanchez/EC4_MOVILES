package pe.edu.idat.SL76130644.view.activity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import pe.edu.idat.SL76130644.R
import pe.edu.idat.SL76130644.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val GOOGLE_SIGN_IN: Int = 1000
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                val account = task.getResult(ApiException::class.java)
                if (account!=null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                            result ->
                        if (result.isSuccessful) {
                            goStartActivity()
                        } else {
                            showAlertError()
                        }
                    }
                } else {
                    showAlertError()
                }
            } catch (e: ApiException) {
                Log.e(TAG, "Error al obtener la cuenta de Google: ${e.message}")
                showAlertError()
            }
        }
    }

    private fun setUpViews() {
        binding.btnlogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {result ->
                        if (result.isSuccessful){
                            goStartActivity()
                        }else{
                            showAlertError()
                        }
                    }
            }else{
                showAlertError()
            }
        }

        binding.btnSignup.setOnClickListener {
            goSignupActivity()
        }

        binding.imgGoogle.setOnClickListener{
            val googleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.CLIENT_ID))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this@LoginActivity, googleSignIn)
            activityResultLauncher.launch(googleClient.signInIntent)
        }
    }

    private fun goSignupActivity() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun showAlertError(){
        val alertBuilder = AlertDialog.Builder(this)
            .setTitle("Ups, ocurrio un error")
            .setMessage("Se produjo un error al iniciar sesión")
            .setPositiveButton("Aceptar", null)
        val alert: AlertDialog = alertBuilder.create()
        alert.show()
    }

    private fun goStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }
}
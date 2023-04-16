package pe.edu.idat.SL76130644.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import pe.edu.idat.SL76130644.model.Password

class PasswordViewModel: ViewModel() {
    private val passwordList: MutableLiveData<List<Password>> by lazy {
        MutableLiveData<List<Password>>().also {
            loadPasswordList()
        }
    }

    fun getPasswordList(): LiveData<List<Password>> {
        return passwordList
    }

    private fun loadPasswordList() {
        FirebaseFirestore.getInstance().collection("Contraseñas")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed.", exception)
                    return@addSnapshotListener
                }

                try {
                    val passwords = snapshot?.documents?.mapNotNull {
                        it.toObject(Password::class.java)
                    }
                    passwordList.value = passwords
                } catch (e: Exception) {
                    Log.e(TAG, "Error loading passwords: ${e.message}")
                }
            }
    }
}
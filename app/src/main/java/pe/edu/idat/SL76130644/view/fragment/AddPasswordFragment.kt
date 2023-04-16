package pe.edu.idat.SL76130644.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.idat.SL76130644.databinding.FragmentAddPasswordBinding

class AddPasswordFragment : Fragment() {

    private lateinit var binding: FragmentAddPasswordBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore

        binding.btnRegistrar.setOnClickListener {
            if (validateFields()) {
                val titulo = binding.etTitulo?.text.toString()
                val cuenta = binding.etCuenta?.text.toString()
                val password = binding.etContraseA?.text.toString()
                val sitio_web = binding.etSitio?.text.toString()
                val contraseñaData = hashMapOf(
                    "titulo" to titulo,
                    "cuenta" to cuenta,
                    "password" to password,
                    "sitio_web" to sitio_web
                )
                db.collection("Contraseñas")
                    .add(contraseñaData)
                    .addOnSuccessListener {
                        Log.d("Register", "DocumentSnapshot added with ID: ${it.id}")
                        showSuccessDialog()
                        clearFields()
                        binding.etTitulo.requestFocus()
                        parentFragmentManager.popBackStack()
                    }
                    .addOnFailureListener{
                        Log.d("Register", "Error")
                    }
            }
        }
    }
    private fun showSuccessDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Registro de contraseña exitoso")
            .setMessage("La contraseña se ha registrado correctamente")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

    private fun clearFields() {
        binding.etTitulo.setText("")
        binding.etCuenta.setText("")
        binding.etContraseA.setText("")
        binding.etSitio.setText("")
    }

    private fun validateFields(): Boolean {
        if (binding.etTitulo.text.isNullOrEmpty() ||
            binding.etCuenta.text.isNullOrEmpty() ||
            binding.etContraseA.text.isNullOrEmpty() ||
            binding.etSitio.text.isNullOrEmpty()
        ) {
            showErrorDialog()
            return false
        }
        return true
    }

    private fun showErrorDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage("Por favor, complete todos los campos.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }
}
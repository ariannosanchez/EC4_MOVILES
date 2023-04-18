package pe.edu.idat.SL76130644.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import pe.edu.idat.SL76130644.R
import pe.edu.idat.SL76130644.databinding.FragmentHomeBinding
import pe.edu.idat.SL76130644.databinding.FragmentLogoutBinding
import pe.edu.idat.SL76130644.view.activity.LoginActivity
import pe.edu.idat.SL76130644.view.activity.SignupActivity

class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro que deseas cerrar sesión?")
            .setPositiveButton("Aceptar") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                goLoginActivity()
            }
            .setNegativeButton("Cancelar", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun goLoginActivity() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
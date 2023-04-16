package pe.edu.idat.SL76130644.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.SL76130644.databinding.ItemPasswordBinding
import pe.edu.idat.SL76130644.model.Password

class PasswordAdapter (var passwordList: List<Password>) :
    RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder>() {

    inner class PasswordViewHolder(private val binding: ItemPasswordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(password: Password) {
            binding.tvTitulo.text = password.titulo
            binding.tvCuenta.text = password.cuenta
            binding.tvPassword.text = password.password
            binding.tvSitioWeb.text = password.sitio_web
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        val binding =
            ItemPasswordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PasswordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        holder.bind(passwordList[position])
    }

    override fun getItemCount(): Int = passwordList.size
}
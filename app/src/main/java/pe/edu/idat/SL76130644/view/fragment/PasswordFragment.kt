package pe.edu.idat.SL76130644.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.SL76130644.viewmodel.PasswordViewModel
import pe.edu.idat.SL76130644.R
import pe.edu.idat.SL76130644.databinding.FragmentPasswordBinding
import pe.edu.idat.SL76130644.view.adapter.PasswordAdapter

class PasswordFragment : Fragment() {
    private lateinit var binding: FragmentPasswordBinding
    private lateinit var passwordAdapter: PasswordAdapter
    private lateinit var passwordViewModel: PasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passwordViewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PasswordAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        passwordViewModel.getPasswordList().observe(viewLifecycleOwner) { passwordList ->
            adapter.passwordList = passwordList
            adapter.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener {
            val fragment = AddPasswordFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_navigation, fragment)
                .commit()
        }
    }
}
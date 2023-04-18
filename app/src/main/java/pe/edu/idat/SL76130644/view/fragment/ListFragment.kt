package pe.edu.idat.SL76130644.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.SL76130644.databinding.FragmentListBinding
import pe.edu.idat.SL76130644.view.adapter.CharacterAdapter
import pe.edu.idat.SL76130644.viewmodel.ViewModelCharacter

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var viewModel: ViewModelCharacter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[ViewModelCharacter::class.java]
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            binding.recyclerView.adapter = CharacterAdapter(characters!!)
            binding.progress.visibility = View.GONE
        })

        if (savedInstanceState == null) {
            viewModel.getCharacters()
        }
    }

    private fun setViews() {
        val linearManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearManager
    }

}
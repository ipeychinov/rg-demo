package co.inanis.rgdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.inanis.rgdemo.databinding.FragmentTranslateBinding
import co.inanis.rgdemo.ui.TranslationViewModel
import co.inanis.rgdemo.ui.adapters.TranslationTypeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslateFragment : Fragment() {

    private var binding: FragmentTranslateBinding? = null
    private lateinit var typeAdapter: TranslationTypeAdapter

    private val translationViewModel by viewModel<TranslationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTranslateBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        assignListeners()
        assignObservers()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupUI() {
        typeAdapter = TranslationTypeAdapter(requireContext())
        binding?.lang?.adapter = typeAdapter
    }

    private fun assignListeners() {
        binding?.translate?.setOnClickListener {
            if (binding?.textToTranslate?.text.isNullOrEmpty()) {
                binding?.textToTranslate?.error = "Enter text first"
            } else {
                translationViewModel.translate(
                    typeAdapter.getItem(binding?.lang?.selectedItemPosition ?: 0),
                    binding?.textToTranslate?.text.toString(),
                )
            }
        }
    }

    private fun assignObservers() {
        translationViewModel.translation.observe(viewLifecycleOwner) {
            binding?.translated?.text = it?.translated
        }
    }

}
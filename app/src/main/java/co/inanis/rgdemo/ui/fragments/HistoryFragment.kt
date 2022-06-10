package co.inanis.rgdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.inanis.rgdemo.databinding.FragmentHistoryBinding
import co.inanis.rgdemo.ui.TranslationViewModel
import co.inanis.rgdemo.ui.adapters.TranslationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var binding: FragmentHistoryBinding? = null
    private val translationAdapter by lazy { TranslationAdapter() }

    private val translationViewModel by viewModel<TranslationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        assignObservers()
        translationViewModel.refreshHistory()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupUI() {
        binding?.root?.apply {
            adapter = translationAdapter
            setHasFixedSize(true)
        }
    }

    private fun assignObservers() {
        translationViewModel.history.observe(viewLifecycleOwner) {
            translationAdapter.submitList(it)
        }
    }

}
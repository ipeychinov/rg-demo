package co.inanis.rgdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.inanis.rgdemo.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var binding: FragmentHistoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}
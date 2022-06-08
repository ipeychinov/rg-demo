package co.inanis.rgdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.inanis.rgdemo.databinding.FragmentTranslateBinding

class TranslateFragment : Fragment() {

    private var binding: FragmentTranslateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTranslateBinding.inflate(inflater)
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}
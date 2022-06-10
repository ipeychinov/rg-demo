package co.inanis.rgdemo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import co.inanis.rgdemo.databinding.ItemDropdownBinding
import co.inanis.rgdemo.model.TranslationType

class TranslationTypeAdapter(private val context: Context) : BaseAdapter() {

    private val data = TranslationType.values()

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): TranslationType = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val binding = if (convertView != null) {
            ItemDropdownBinding.bind(convertView)
        } else {
            ItemDropdownBinding.inflate(LayoutInflater.from(context), parent, false)
        }

        binding.text1.text = data[position].readableName()

        return binding.root
    }

}
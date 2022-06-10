package co.inanis.rgdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.inanis.rgdemo.databinding.ItemTranslationBinding
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationComparator

class TranslationAdapter : ListAdapter<Translation, TranslationAdapter.TranslationViewHolder>(
    TranslationComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val binding =
            ItemTranslationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TranslationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TranslationViewHolder(
        private val binding: ItemTranslationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(translation: Translation?) {
            binding.text.text = translation?.text
            binding.type.text = "In ${translation?.type?.readableName()}"
            binding.translated.text = translation?.translated
        }
    }

}
package co.inanis.rgdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cafe.adriel.broker.GlobalBroker
import cafe.adriel.broker.publish
import co.inanis.rgdemo.data.translations.TranslationRepository
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType
import co.inanis.rgdemo.utils.ErrorEvent
import kotlinx.coroutines.launch

class TranslationViewModel(
    private val repository: TranslationRepository
) : ViewModel(), GlobalBroker.Publisher {

    private val _translation = MutableLiveData<Translation?>()
    val translation = liveData {
        emitSource(_translation)
    }

    fun translate(to: TranslationType, text: String) {
        viewModelScope.launch {
            repository.translate(to, text).either(::onError) {
                _translation.value = it.contents
            }
        }
    }

    private val _history = MutableLiveData<List<Translation>>()
    val history = liveData {
        emit(repository.getTranslationHistory())
        emitSource(_history)
    }

    fun refreshHistory() {
        viewModelScope.launch {
            _history.value = repository.getTranslationHistory()
        }
    }

    private fun onError(message: String?) = publish(ErrorEvent(message))

}
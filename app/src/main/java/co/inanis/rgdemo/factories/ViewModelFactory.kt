package co.inanis.rgdemo.factories

import co.inanis.rgdemo.ui.TranslationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel { TranslationViewModel(get()) }

}
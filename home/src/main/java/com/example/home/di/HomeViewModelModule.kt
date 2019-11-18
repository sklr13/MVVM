package com.example.home.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(myViewModel: HomeViewModel): ViewModel
}
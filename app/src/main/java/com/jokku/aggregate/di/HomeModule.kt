package com.jokku.aggregate.di

import com.jokku.aggregate.data.repo.MainNewsRepository
import com.jokku.aggregate.data.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindNewsRepository(impl: MainNewsRepository) : NewsRepository
}
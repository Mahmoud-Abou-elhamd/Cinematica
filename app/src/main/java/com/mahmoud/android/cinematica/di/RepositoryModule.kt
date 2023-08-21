package com.mahmoud.android.cinematica.di

import com.mahmoud.android.cinematica.data.repository.MovieRepository
import com.mahmoud.android.cinematica.data.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @ViewModelScoped
    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository
}
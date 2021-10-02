package pe.edu.upc.superherocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pe.edu.upc.superherocompose.data.local.HeroDao
import pe.edu.upc.superherocompose.data.remote.HeroService
import pe.edu.upc.superherocompose.repository.HeroRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideHeroRepository(
        heroService: HeroService,
        heroDao: HeroDao
    ): HeroRepository {
        return HeroRepository(heroService, heroDao)
    }
}
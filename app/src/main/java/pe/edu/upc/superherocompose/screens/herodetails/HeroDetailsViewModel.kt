package pe.edu.upc.superherocompose.screens.herodetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.repository.HeroRepository
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(private val repository: HeroRepository) :
    ViewModel() {

    private var _hero = MutableLiveData<Hero>()
    val hero get() = _hero

    fun fetchHeroById(id: String) {
        viewModelScope.launch {
            _hero.postValue(repository.fetchHeroById(id))
        }

    }

}
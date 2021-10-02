package pe.edu.upc.superherocompose.screens.herolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.superherocompose.data.model.Hero
import pe.edu.upc.superherocompose.repository.HeroRepository
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val heroRepository: HeroRepository) :
    ViewModel() {

    private var _heroes = MutableLiveData<List<Hero>>()
    val heroes get() = _heroes

    fun fetchHeroesByName(name: String) {
        viewModelScope.launch {
            _heroes.postValue(heroRepository.fetchHeroesByName(name))
        }
    }

    fun delete(hero: Hero) {
        viewModelScope.launch {
            heroRepository.delete(hero)
        }
    }

    fun insert(hero: Hero) {
        viewModelScope.launch {
            heroRepository.insert(hero)
        }
    }
}
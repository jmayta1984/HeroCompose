/*
 * Designed and developed by 2021 jmayta1984 (Jorge Mayta)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.edu.upc.herocompose.ui.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.herocompose.data.model.Hero
import pe.edu.upc.herocompose.repository.HeroRepository
import pe.edu.upc.herocompose.utils.Resource
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val heroRepository: HeroRepository) :
    ViewModel() {

    private var _heroes = MutableLiveData<List<Hero>>()
    val heroes get() = _heroes

    private var _name = MutableLiveData<String>()
    val name get() = _name

    fun update(name: String) {
        _name.value = name
    }

    fun fetchHeroesByName(name: String) {
        viewModelScope.launch {
            val result = heroRepository.fetchHeroesByName(name)
            if (result is Resource.Success) {
                _heroes.value = result.data!!
            }
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
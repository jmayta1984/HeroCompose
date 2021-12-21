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

package pe.edu.upc.herocompose.repository

import pe.edu.upc.herocompose.data.model.Hero
import pe.edu.upc.herocompose.utils.Resource

interface HeroRepository {
    suspend fun update(hero: Hero)
    suspend fun fetchHeroesByName(name: String): Resource<List<Hero>>
    suspend fun fetchHeroById(id: String): Resource<Hero>
    suspend fun delete(hero: Hero)
    suspend fun insert(hero: Hero)
}
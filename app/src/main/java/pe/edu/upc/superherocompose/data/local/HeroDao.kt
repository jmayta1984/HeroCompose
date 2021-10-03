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

package pe.edu.upc.superherocompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.superherocompose.data.model.Hero

@Dao
interface HeroDao {

    @Query("select * from heroes where id=:id")
    suspend fun fetchHeroById(id: String): Hero?

    @Insert
    suspend fun insert(hero: Hero)

    @Delete
    suspend fun delete(hero: Hero)
}
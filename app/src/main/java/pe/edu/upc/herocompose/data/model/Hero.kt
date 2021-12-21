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

package pe.edu.upc.herocompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "hero_name")
    val name: String,

    @ColumnInfo(name = "hero_full_name")
    var fullName: String,

    @ColumnInfo(name = "hero_image_url")
    var imageUrl: String
) {
    constructor() : this("", "", "", "")

    @Ignore
    val biography: Biography = Biography()

    @Ignore
    @SerializedName("powerstats")
    val powerStats: PowerStats = PowerStats()

    @Ignore
    val image: Image = Image()

    @Ignore
    var favorite: Boolean = false
}

data class Biography(
    @SerializedName("full-name")
    val fullName: String,

    @SerializedName("place-of-birth")
    val birthPlace: String,

    val publisher: String
) {
    constructor() : this("", "", "")
}

data class Image(
    val url: String
) {
    constructor() : this("")
}

class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
) {
    constructor() : this("", "", "", "", "", "")
}
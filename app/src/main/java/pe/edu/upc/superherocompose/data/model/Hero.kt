package pe.edu.upc.superherocompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
class Hero(

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
    val image: Image = Image()

    @Ignore
    var favorite: Boolean = false
}


class Biography(
    @SerializedName("full-name")
    val fullName: String
) {
    constructor() : this("")
}

class Image(
    val url: String
) {
    constructor() : this("")
}
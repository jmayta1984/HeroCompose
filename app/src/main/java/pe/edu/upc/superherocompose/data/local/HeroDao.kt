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
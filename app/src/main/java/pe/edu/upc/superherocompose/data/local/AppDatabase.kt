package pe.edu.upc.superherocompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.upc.superherocompose.data.model.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, AppDatabase::class.java, "heroes.dn").build()
            }
            return INSTANCE as AppDatabase
        }

    }
}
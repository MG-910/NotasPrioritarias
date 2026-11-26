package com.ut.notas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ut.notas.data.dao.NotaDao
import com.ut.notas.data.entity.CategoriaEntity
import com.ut.notas.data.entity.EtiquetaEntity
import com.ut.notas.data.entity.ImportanciaEntity
import com.ut.notas.data.entity.NotaEntity

@Database(
    entities = [NotaEntity::class, CategoriaEntity::class, ImportanciaEntity::class, EtiquetaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Patrón Singleton
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
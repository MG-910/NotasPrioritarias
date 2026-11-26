package com.ut.notas.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ut.notas.data.AppDatabase
import com.ut.notas.data.dao.NotaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "notas_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Insertamos una categoría y 3 importancias por defecto
                db.execSQL("INSERT INTO categorias (nombre) VALUES ('General')")
                db.execSQL("INSERT INTO importancias (nivel, colorHex) VALUES ('Alta', '#FF0000')")
                db.execSQL("INSERT INTO importancias (nivel, colorHex) VALUES ('Media', '#FFA500')")
                db.execSQL("INSERT INTO importancias (nivel, colorHex) VALUES ('Baja', '#008000')")
            }
        }).build()
    }

    @Provides
    fun provideNotaDao(database: AppDatabase): NotaDao {
        return database.notaDao()
    }
}
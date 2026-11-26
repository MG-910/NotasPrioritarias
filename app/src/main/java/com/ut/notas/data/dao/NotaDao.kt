package com.ut.notas.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ut.notas.data.entity.NotaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {
    @Query("SELECT * FROM notas ORDER BY importanciaId DESC, fechaModificacion DESC")
    fun obtenerTodas(): Flow<List<NotaEntity>>

    @Insert
    suspend fun insertar(nota: NotaEntity)

    @Update
    suspend fun actualizar(nota: NotaEntity)

    @Delete
    suspend fun eliminar(nota: NotaEntity)
}
package com.ut.notas.domain.repository

import com.ut.notas.domain.model.Nota
import kotlinx.coroutines.flow.Flow

interface NotaRepository {
    fun obtenerTodas(): Flow<List<Nota>>
    suspend fun insertar(nota: Nota)
    suspend fun actualizar(nota: Nota)
    suspend fun eliminar(nota: Nota)
}
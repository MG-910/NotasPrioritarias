package com.ut.notas.data.repository

import com.ut.notas.data.dao.NotaDao
import com.ut.notas.data.mapper.toDomain
import com.ut.notas.data.mapper.toEntity
import com.ut.notas.domain.model.Nota
import com.ut.notas.domain.repository.NotaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotaRepositoryImp @Inject constructor(
    private val notaDao: NotaDao
) : NotaRepository {

    override fun obtenerTodas(): Flow<List<Nota>> {
        return notaDao.obtenerTodas().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun insertar(nota: Nota) {
        notaDao.insertar(nota.toEntity())
    }

    override suspend fun actualizar(nota: Nota) {
        notaDao.actualizar(nota.toEntity())
    }

    override suspend fun eliminar(nota: Nota) {
        notaDao.eliminar(nota.toEntity())
    }
}
package com.ut.notas.data.mapper

import com.ut.notas.data.entity.NotaEntity
import com.ut.notas.domain.model.Nota

fun NotaEntity.toDomain(): Nota = Nota(
    id = id,
    titulo = titulo,
    contenido = contenido,
    categoriaId = categoriaId,
    importanciaId = importanciaId,
    fechaModificacion = fechaModificacion
)

fun Nota.toEntity(): NotaEntity = NotaEntity(
    id = id,
    titulo = titulo,
    contenido = contenido,
    categoriaId = categoriaId,
    importanciaId = importanciaId,
    fechaModificacion = fechaModificacion
)
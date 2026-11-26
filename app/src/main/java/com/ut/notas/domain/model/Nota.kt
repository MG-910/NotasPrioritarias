package com.ut.notas.domain.model

data class Nota(
    val id: Int = 0,
    val titulo: String,
    val contenido: String,
    val categoriaId: Int,
    val importanciaId: Int,
    val fechaModificacion: Long
)
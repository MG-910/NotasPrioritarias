package com.ut.notas.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notas",
    foreignKeys = [
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoriaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ImportanciaEntity::class,
            parentColumns = ["id"],
            childColumns = ["importanciaId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val contenido: String,
    val categoriaId: Int,
    val importanciaId: Int,
    val fechaModificacion: Long = System.currentTimeMillis()
)
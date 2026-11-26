package com.ut.notas.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "importancias")
data class ImportanciaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nivel: String, // "Baja", "Media", "Alta"
    val colorHex: String // Ejemplo: "#FF0000"
)
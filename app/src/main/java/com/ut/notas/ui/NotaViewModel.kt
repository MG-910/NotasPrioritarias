package com.ut.notas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ut.notas.domain.model.Nota
import com.ut.notas.domain.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotaViewModel @Inject constructor(
    private val repository: NotaRepository
) : ViewModel() {

    val notas: StateFlow<List<Nota>> = repository.obtenerTodas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Variable para mostrar errores en la pantalla
    val mensajeError = MutableStateFlow<String?>(null)

    fun guardarNota(titulo: String, contenido: String) {
        if (titulo.isBlank()) {
            mensajeError.value = "El título no puede estar vacío"
            return
        }
        viewModelScope.launch {
            try {
                repository.insertar(
                    Nota(
                        titulo = titulo.trim(),
                        contenido = contenido.trim(),
                        categoriaId = 1,
                        importanciaId = 1,
                        fechaModificacion = System.currentTimeMillis()
                    )
                )
                mensajeError.value = null // Limpiar error si guarda bien
            } catch (e: Exception) {
                // Si falla, mostramos el error en la pantalla
                mensajeError.value = "Error al guardar: ${e.message}"
            }
        }
    }

    fun eliminarNota(nota: Nota) {
        viewModelScope.launch {
            try {
                repository.eliminar(nota)
            } catch (e: Exception) {
                mensajeError.value = "Error al eliminar: ${e.message}"
            }
        }
    }
}
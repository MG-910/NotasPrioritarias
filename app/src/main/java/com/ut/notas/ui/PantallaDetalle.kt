package com.ut.notas.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ut.notas.R
import com.ut.notas.domain.model.Nota

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    nota: Nota,
    onGuardar: (Nota) -> Unit,
    onCancelar: () -> Unit
) {
    var titulo by remember { mutableStateOf(nota.titulo) }
    var contenido by remember { mutableStateOf(nota.contenido) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Nota") },
                navigationIcon = {
                    TextButton(onClick = onCancelar) {
                        Text(stringResource(id = R.string.btn_cancelar))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text(stringResource(id = R.string.hint_titulo)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contenido,
                onValueChange = { contenido = it },
                label = { Text(stringResource(id = R.string.hint_contenido)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 5
            )
            Button(
                onClick = {
                    val notaActualizada = nota.copy(titulo = titulo, contenido = contenido)
                    onGuardar(notaActualizada)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.btn_guardar))
            }
        }
    }
}
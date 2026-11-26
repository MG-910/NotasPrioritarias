package com.ut.notas.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ut.notas.R
import com.ut.notas.domain.model.Nota

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLista(
    viewModel: NotaViewModel = hiltViewModel(),
    onNotaClick: (Nota) -> Unit = {}
) {
    val notas by viewModel.notas.collectAsStateWithLifecycle()

    // 1. Aquí se declara la variable del mensaje de error
    val mensajeError by viewModel.mensajeError.collectAsStateWithLifecycle()

    var mostrarDialogo by remember { mutableStateOf(false) }
    var titulo by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.titulo_pantalla)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) { paddingValues ->

        // 2. Aquí va el AlertDialog para mostrar el error
        if (mensajeError != null) {
            AlertDialog(
                onDismissRequest = { viewModel.mensajeError.value = null },
                title = { Text("Atención") },
                text = { Text(mensajeError!!) },
                confirmButton = {
                    TextButton(onClick = { viewModel.mensajeError.value = null }) {
                        Text("OK")
                    }
                }
            )
        }

        if (notas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.sin_notas))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notas) { nota ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNotaClick(nota) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = nota.titulo, style = MaterialTheme.typography.titleMedium)
                            Text(text = nota.contenido, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text(stringResource(id = R.string.nueva_nota)) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = titulo,
                            onValueChange = { titulo = it },
                            label = { Text(stringResource(id = R.string.hint_titulo)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = contenido,
                            onValueChange = { contenido = it },
                            label = { Text(stringResource(id = R.string.hint_contenido)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.guardarNota(titulo, contenido)
                        titulo = ""
                        contenido = ""
                        mostrarDialogo = false
                    }) {
                        Text(stringResource(id = R.string.btn_guardar))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text(stringResource(id = R.string.btn_cancelar))
                    }
                }
            )
        }
    }
}
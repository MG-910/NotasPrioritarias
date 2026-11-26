package com.ut.notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ut.notas.domain.model.Nota
import com.ut.notas.ui.PantallaDetalle
import com.ut.notas.ui.PantallaLista
import com.ut.notas.ui.theme.NotasPrioritariasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotasPrioritariasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Configuración de Navegación (Pregunta 16)
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "lista") {
                        composable("lista") {
                            PantallaLista(
                                onNotaClick = { nota ->
                                    // Aquí podríamos pasar el ID de la nota,
                                    // pero para simplificar, solo navegamos.
                                    navController.navigate("detalle")
                                }
                            )
                        }
                        composable("detalle") {
                            // Nota de prueba solo para demostrar la navegación
                            val notaVacia = Nota(titulo = "", contenido = "", categoriaId = 1, importanciaId = 1, fechaModificacion = 0L)

                            PantallaDetalle(
                                nota = notaVacia,
                                onGuardar = {
                                    // Aquí iría la lógica para guardar los cambios
                                    navController.popBackStack()
                                },
                                onCancelar = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
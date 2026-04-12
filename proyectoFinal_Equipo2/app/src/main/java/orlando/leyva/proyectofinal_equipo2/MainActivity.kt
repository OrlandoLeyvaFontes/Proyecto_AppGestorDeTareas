package orlando.leyva.proyectofinal_equipo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import orlando.leyva.proyectofinal_equipo2.ui.theme.ProyectoFinal_Equipo2Theme
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaEstadisticas
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaEstadisticasHogar
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaLogin
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaRegistro
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaFormTarea
import orlando.leyva.proyectofinal_equipo2.ui.screens.PantallaPersonalizarPerfil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinal_Equipo2Theme {
                val currentScreen = remember { mutableStateOf("agregarTarea") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen.value) {
                            "login" -> PantallaLogin(
                                onLogin = { currentScreen.value = "general" },
                                onRegister = { currentScreen.value = "registro" }
                            )
                            "registro" -> PantallaRegistro(
                                onBackToLogin = { currentScreen.value = "login" },
                                onRegisterClick = { currentScreen.value = "login" }
                            )
                            "general" -> PantallaEstadisticas(
                                onBackClick = { currentScreen.value = "hogar" }
                            )
                            "hogar" -> PantallaEstadisticasHogar(
                                onBack = { currentScreen.value = "general" }
                            )
                            "agregarTarea" -> PantallaFormTarea(
                                modoEditar = false,
                                onBackClick = { currentScreen.value = "hogar" },
                                onAceptarClick = { currentScreen.value = "hogar" }
                            )
                            "editarTarea" -> PantallaFormTarea(
                                modoEditar = true,
                                onBackClick = { currentScreen.value = "hogar" },
                                onAceptarClick = { currentScreen.value = "hogar" }
                            )
                            "personalizarPerfil" -> {
                                PantallaPersonalizarPerfil(
                                    onBackClick = { currentScreen.value = "hogar" },
                                    onAceptarClick = { currentScreen.value = "hogar" }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

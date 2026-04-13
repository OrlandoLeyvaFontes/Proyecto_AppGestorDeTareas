package orlando.leyva.proyectofinal_equipo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import orlando.leyva.proyectofinal_equipo2.ui.screens.*
import orlando.leyva.proyectofinal_equipo2.ui.theme.ProyectoFinal_Equipo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProyectoFinal_Equipo2Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        PantallaLogin(
                            onLogin = {
                                navController.navigate("home_houses") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // CASAS
                    composable("home_houses") {
                        HomeHouseScreen(navController)
                    }

                    // TAREAS
                    composable("home_tasks") {
                        HomeScreen(navController)
                    }

                    // DETALLE TAREA
                    composable("task_detail") {
                        PantallaDetalleTarea(navController)
                    }

                    // CONFIG CASA
                    composable("config_house") {
                        PantallaConfigurarHogar(navController)
                    }

                    // CREAR CASA
                    composable("create_house") {
                        CreateHouseScreen(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // UNIR CASA
                    composable("join_house") {
                        JoinHouseScreen(
                            onBack = { navController.popBackStack() },
                            onCancel = { navController.popBackStack() }
                        )
                    }

                    // 🔥 IMPORTANTE: estos eran los que te faltaban y te estaban rompiendo el BottomBar

                    composable("estadisticas") {
                        PantallaEstadisticas(
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("personalizar_perfil") {
                        PantallaPersonalizarPerfil(
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("form_tarea") {
                        PantallaFormTarea(
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                }
            }
        }
    }
}
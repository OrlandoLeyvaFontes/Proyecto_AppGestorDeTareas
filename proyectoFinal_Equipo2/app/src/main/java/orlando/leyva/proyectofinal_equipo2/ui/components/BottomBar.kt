package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("home_houses") {
                    popUpTo("home_houses") { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { /* tu icono Home */ },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("estadisticas") {
                    launchSingleTop = true
                }
            },
            icon = { /* tu icono Stats */ },
            label = { Text("Stats") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("personalizar_perfil") {
                    launchSingleTop = true
                }
            },
            icon = { /* tu icono Config */ },
            label = { Text("Config") }
        )
    }
}
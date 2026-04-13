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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import orlando.leyva.proyectofinal_equipo2.R

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
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home", modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("estadisticas") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.stats),
                    contentDescription = "Home", modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Stats") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("personalizar_perfil") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.config),
                    contentDescription = "Home", modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Config") }
        )
    }
}
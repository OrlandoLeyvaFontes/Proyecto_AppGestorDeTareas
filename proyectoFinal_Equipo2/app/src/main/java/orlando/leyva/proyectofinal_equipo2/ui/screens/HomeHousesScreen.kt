package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import orlando.leyva.proyectofinal_equipo2.ui.components.BottomBar
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.components.HouseItem
import orlando.leyva.proyectofinal_equipo2.ui.components.MainButton
import orlando.leyva.proyectofinal_equipo2.model.House
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro
import orlando.leyva.proyectofinal_equipo2.R

import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun HomeHouseScreenPreview() {
    val navController = rememberNavController()
    HomeHouseScreen(navController = navController)
}

@Composable
fun HomeHouseScreen(
    navController: NavController
) {

    val gradiente = Brush.horizontalGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    val casas = listOf(
        House("Casa Familia Leyva", Color(0xFF1E4DB7)),
        House("Casa Playa", Color(0xFF2E7D32))
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradiente)
                .padding(padding)
        ) {

            Header(titulo = "Menú principal",
                mostrarBack = false
                )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    casas.forEach { casa ->
                        HouseItem(
                            house = casa,
                            onClick = {
                                navController.navigate("home_tasks")
                            },
                            onEditClick = {
                                navController.navigate("config_house")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    MainButton(
                        texto = "Crear hogar",
                        icono = R.drawable.create
                    ) {
                        navController.navigate("create_house")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    MainButton(
                        texto = "Unirse a hogar",
                        icono = R.drawable.join
                    ) {
                        navController.navigate("join_house")
                    }
                }
            }
        }
    }
}
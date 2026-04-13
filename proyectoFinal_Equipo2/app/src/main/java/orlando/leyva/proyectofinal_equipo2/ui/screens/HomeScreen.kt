package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import kotlin.collections.listOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.ui.components.BottomBar
import orlando.leyva.proyectofinal_equipo2.ui.components.DaySection
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.components.ProgressSection
import orlando.leyva.proyectofinal_equipo2.ui.components.ToggleTask
import orlando.leyva.proyectofinal_equipo2.model.DayTasks
import orlando.leyva.proyectofinal_equipo2.model.Task
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

@Composable
fun HomeScreen(
    navController: NavController
) {

    val gradienteFondo = Brush.horizontalGradient(
        colors = listOf(VerdeOscuro, VerdeClaro)
    )

    val datosDummy = listOf(
        DayTasks("LUNES", listOf(Task("Pagar luz", "Orlando Leyva", "Pendiente"))),
        DayTasks("MARTES", listOf(Task("Sacar basura", "Luis Murillo", "Pendiente"))),
        DayTasks("MIÉRCOLES", listOf(Task("Barrer patio", "Orlando Leyva", "Pendiente"))),
        DayTasks("JUEVES", listOf(Task("Pasear al perro", "Nomar Limón", "Pendiente"))),
        DayTasks("VIERNES", listOf(Task("Lavar el carro", "Nomar Limón", "Pendiente"))),
        DayTasks("SÁBADO", listOf(Task("Limpieza profunda", "Orlando Leyva", "Pendiente"))),
        DayTasks("DOMINGO", listOf(Task("Ir a comprar mandado", "Nomar Limón", "Pendiente")))
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("form_tarea")
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Agregar",
                    modifier = Modifier.size(52.dp)
                )
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradienteFondo)
                .padding(top = padding.calculateTopPadding())
        ) {

            Column {


                Header(
                    titulo = "Inicio",
                    mostrarBack = false,
                    onBack = {}
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Casa Familia Leyva", color = Color.White)
                    Text("Código: 1834", color = Color.White)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-25).dp),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        ToggleTask()

                        Spacer(modifier = Modifier.height(16.dp))

                        ProgressSection(0.17f)

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn {
                            items(datosDummy) { dia ->
                                DaySection(dia, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
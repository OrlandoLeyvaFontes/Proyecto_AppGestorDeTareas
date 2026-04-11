package orlando.leyva.proyectofinal_equipo2.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import kotlin.collections.listOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import orlando.leyva.proyectofinal_equipo2.components.DaySection
import orlando.leyva.proyectofinal_equipo2.components.Header
import orlando.leyva.proyectofinal_equipo2.components.ProgressSection
import orlando.leyva.proyectofinal_equipo2.components.ToggleTask
import orlando.leyva.proyectofinal_equipo2.model.DayTasks
import orlando.leyva.proyectofinal_equipo2.model.Task
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen() {

    val gradienteFondo = Brush.verticalGradient(
        colors = listOf(VerdeClaro, VerdeOscuro)
    )

    val datosDummy = listOf(
        DayTasks(
            "LUNES", listOf(
                Task("Pagar luz", "Orlando Leyva", "Pendiente")
            )
        ),
        DayTasks("MARTES", listOf(
            Task("Sacar basura", "Luis Murillo", "Pendiente")
        )),
        DayTasks("MIÉRCOLES", listOf(
            Task("Barrer patio", "Orlando Leyva", "Pendiente")
        )),
        DayTasks("JUEVES", listOf(
            Task("Pasear al perro", "Nomar Limón", "Pendiente")
        ))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradienteFondo)
    ) {

        Column {

            Header()

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    ToggleTask()

                    Spacer(modifier = Modifier.height(16.dp))

                    ProgressSection(0.62f)

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        items(datosDummy) { dia ->
                            DaySection(dia)
                        }
                    }
                }
            }
        }
    }



}
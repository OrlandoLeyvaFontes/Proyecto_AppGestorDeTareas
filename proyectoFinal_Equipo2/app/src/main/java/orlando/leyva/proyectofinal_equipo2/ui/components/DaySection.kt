package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.model.DayTasks
import orlando.leyva.proyectofinal_equipo2.model.Task

@Composable
fun DaySection(
    dayTasks: DayTasks,
    navController: NavController
) {

    var expandido by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandido = !expandido }
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(dayTasks.dia, fontWeight = FontWeight.Bold)
        }

        if (expandido) {
            dayTasks.tareas.forEach { task ->
                TaskCard(
                    task = task,
                    onClick = {
                        navController.navigate("task_detail")
                    }
                )
            }
        }
    }
}
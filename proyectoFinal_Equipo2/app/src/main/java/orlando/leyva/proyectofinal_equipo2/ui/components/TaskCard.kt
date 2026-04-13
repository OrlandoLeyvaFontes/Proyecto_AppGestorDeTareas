package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.model.Task
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Composable
fun TaskCard(
    task: Task,
    onClick: () -> Unit = {}
) {

    val gradiente = Brush.linearGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(gradiente, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {

        Column {
            Text(task.titulo, color = Color.White)
            Text(task.responsable, color = Color.White, fontSize = 12.sp)
        }

        Text(
            task.estado,
            color = Color.White,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}
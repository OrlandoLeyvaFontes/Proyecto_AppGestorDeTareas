package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro

@Composable
fun ProgressSection(progreso: Float) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Progreso diario")
            Text("${(progreso * 100).toInt()}%")
        }

        LinearProgressIndicator(
            progress = progreso,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = VerdeClaro
        )
    }
}
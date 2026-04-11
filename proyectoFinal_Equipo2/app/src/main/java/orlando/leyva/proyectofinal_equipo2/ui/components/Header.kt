package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Composable
fun Header(
    titulo: String,
    mostrarBack: Boolean = true
) {

    val gradiente = Brush.horizontalGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradiente)
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            if (mostrarBack) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Regresar",
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = "Taskify",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


    }
}
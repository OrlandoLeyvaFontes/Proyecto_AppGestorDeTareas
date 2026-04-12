package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Composable
fun MainButton(
    texto: String,
    icono: Int,
    onClick: () -> Unit
) {

    val gradiente = Brush.horizontalGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(gradiente, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(4.dp)
                .height(40.dp)
                .background(Color.White, RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = texto,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(id = icono),
            contentDescription = texto,
            modifier = Modifier.size(28.dp)
        )
    }
}
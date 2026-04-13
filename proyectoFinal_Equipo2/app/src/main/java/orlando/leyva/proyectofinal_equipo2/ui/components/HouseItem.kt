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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.model.House

@Composable
fun HouseItem(
    house: House,
    onClick: () -> Unit,
    onEditClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(house.color, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.house),
            contentDescription = "Casa",
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = house.nombre,
            modifier = Modifier.weight(1f),
            color = Color.White
        )

        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Editar"
            )
        }

    }
}
package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import orlando.leyva.proyectofinal_equipo2.R


@Composable
fun BottomBar() {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.stats),
                    contentDescription = "Estadísticas",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Stats") }
        )

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Inicio") }
        )


        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.config),
                    contentDescription = "Config",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Config") }
        )
    }
}
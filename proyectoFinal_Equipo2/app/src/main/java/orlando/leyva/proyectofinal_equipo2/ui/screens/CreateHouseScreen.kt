package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Preview(showBackground = true)
@Composable
fun CreateHouseScreenPreview() {
    CreateHouseScreen(
        onBack = {}
    )
}

@Composable
fun CreateHouseScreen(onBack: () -> Unit) {

    var nombre by remember { mutableStateOf("") }

    val gradiente = Brush.horizontalGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradiente)
    ) {

        Header(
            titulo = "Crear hogar",
            mostrarBack = true,
            onBack = onBack
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-12).dp),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Código de invitación: 0814")

                Spacer(modifier = Modifier.height(24.dp))

                Text("Nombre")

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeClaro
                    )
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
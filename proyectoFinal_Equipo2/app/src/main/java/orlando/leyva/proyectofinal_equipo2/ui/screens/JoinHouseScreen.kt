package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
fun JoinHouseScreenPreview() {
    JoinHouseScreen(
        onBack = {},
        onCancel = {}
    )
}


@Composable
fun JoinHouseScreen(
    onBack: () -> Unit = {},
    onCancel: () -> Unit = {}
) {

    var codigo by remember { mutableStateOf("") }

    val gradiente = Brush.horizontalGradient(
        listOf(VerdeOscuro, VerdeClaro)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradiente)
    ) {

        Header(
            titulo = "Unirse a hogar",
            mostrarBack = true,
            onBack = onBack
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (0).dp),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                Card(

                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text("Código")

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = codigo,
                            onValueChange = { codigo = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = VerdeClaro
                            )
                        ) {
                            Text("Unirse")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                Button(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Cancelar")
                }
            }}}}
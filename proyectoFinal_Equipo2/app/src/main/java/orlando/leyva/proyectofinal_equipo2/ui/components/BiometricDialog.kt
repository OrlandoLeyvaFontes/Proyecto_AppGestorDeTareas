package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro

@Composable
fun BiometricDialog(
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {
    var isSuccess by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    // TODO: Actualmente esta para solo PantallaPersonalizaPerfil.kt por eso dice "Se han realizado cambios" pero deberia cambiar el mensaje segun en la pantalla que se encuentre
                    text = if (isSuccess) "Se han realizado los cambios con éxito" else "Iniciar con biometria",
                    color = if (isSuccess) VerdeClaro else Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Icon(
                    imageVector = if (isSuccess) Icons.Default.CheckCircle else Icons.Default.Fingerprint,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { 
                            isSuccess = true
                            // TODO: Podriamos añadir un delay antes de llamar a onSuccess si quisieramos cerrar automatico.
                            // Por ahora lo dejamos para que el usuario vea el check verde
                        },
                    tint = if (isSuccess) VerdeClaro else Color.Black
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (isSuccess) "Cerrar" else "Cancelar",
                        color = VerdeClaro,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable { 
                                if (isSuccess) onSuccess()
                                onDismiss() 
                            }
                            .align(Alignment.CenterStart)
                    )
                }
            }
        }
    }
}

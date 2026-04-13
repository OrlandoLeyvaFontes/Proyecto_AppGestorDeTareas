package orlando.leyva.proyectofinal_equipo2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import orlando.leyva.proyectofinal_equipo2.ui.theme.*

@Composable
fun MemberSelectionDialog(
    currentSelected: List<String>,
    onDismiss: () -> Unit,
    onConfirm: (List<String>) -> Unit
) {
    // Lista de miembros de ejemplo
    val miembrosDisponibles = listOf("Nomar Limon", "Orlando Leyva", "Luis Murillo", "---")
    
    val tempSelected = remember { mutableStateListOf<String>().apply { addAll(currentSelected) } }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F7)), // Color crema claro como en la imagen
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
                    text = "Agregar miembro(s)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Tabla de miembros
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                ) {
                    miembrosDisponibles.forEachIndexed { index, nombre ->
                        val isSelected = tempSelected.contains(nombre) && nombre != "---"
                        
                        if (index > 0) {
                            HorizontalDivider(color = Color.LightGray)
                        }
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (isSelected) Color(0xFFD3D3D3) else Color.Transparent)
                                .clickable(enabled = nombre != "---") {
                                    if (isSelected) tempSelected.remove(nombre)
                                    else tempSelected.add(nombre)
                                }
                                .padding(vertical = 12.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                Text(
                                    text = nombre,
                                    fontSize = 16.sp,
                                    color = if (nombre == "---") Color.Gray else Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.DarkGray
                                )
                            } else if (nombre != "---") {
                                // Spacer para mantener el alineamiento si hay iconos
                                Spacer(modifier = Modifier.width(20.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = RojoBoton),
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancelar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Button(
                        onClick = { onConfirm(tempSelected.toList()) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25B33D)), // Verde de la imagen
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Aceptar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

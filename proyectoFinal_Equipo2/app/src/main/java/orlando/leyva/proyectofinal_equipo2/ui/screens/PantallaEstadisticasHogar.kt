package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEstadisticasHogar(onBack: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoVerde)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Retroceder",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp).clickable { onBack() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Task", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Text("ify", color = Color(0xFF4ADE80), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logotipo",
                        modifier = Modifier.size(36.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.icono_usuario),
                    contentDescription = "Perfil del Usuario",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Estadísticas Hogar Familia Leyva",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            color = GrisSuperficie
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                
                val expandidoPeriodo = remember { mutableStateOf(false) }
                val periodoSeleccionado = remember { mutableStateOf("Mes") }
                val listaPeriodos = listOf("Semana", "Mes", "Año")

                val expandidoFecha = remember { mutableStateOf(false) }
                val fechaSeleccionada = remember { mutableStateOf("Marzo 2026") }
                val listaFechas = listOf("Enero 2026", "Febrero 2026", "Marzo 2026")

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Período", fontSize = 12.sp, color = GrisTexto)
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandidoPeriodo.value,
                            onExpandedChange = { expandidoPeriodo.value = !expandidoPeriodo.value }
                        ) {
                            OutlinedTextField(
                                value = periodoSeleccionado.value,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoPeriodo.value) },
                                modifier = Modifier.fillMaxWidth().height(48.dp).menuAnchor(),
                                shape = RoundedCornerShape(8.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                            )
                            ExposedDropdownMenu(
                                expanded = expandidoPeriodo.value,
                                onDismissRequest = { expandidoPeriodo.value = false }
                            ) {
                                listaPeriodos.forEach { seleccion ->
                                    DropdownMenuItem(
                                        text = { Text(seleccion) },
                                        onClick = {
                                            periodoSeleccionado.value = seleccion
                                            expandidoPeriodo.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Fecha", fontSize = 12.sp, color = GrisTexto)
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandidoFecha.value,
                            onExpandedChange = { expandidoFecha.value = !expandidoFecha.value }
                        ) {
                            OutlinedTextField(
                                value = fechaSeleccionada.value,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoFecha.value) },
                                modifier = Modifier.fillMaxWidth().height(48.dp).menuAnchor(),
                                shape = RoundedCornerShape(8.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                            )
                            ExposedDropdownMenu(
                                expanded = expandidoFecha.value,
                                onDismissRequest = { expandidoFecha.value = false }
                            ) {
                                listaFechas.forEach { seleccion ->
                                    DropdownMenuItem(
                                        text = { Text(seleccion) },
                                        onClick = {
                                            fechaSeleccionada.value = seleccion
                                            expandidoFecha.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(VerdePrimario)
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("Completadas", color = Color.White, fontSize = 12.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("160", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White)
                            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("No realizadas", color = GrisTexto, fontSize = 12.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("29", color = RojoAcento, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier.align(Alignment.TopEnd).size(24.dp).clip(CircleShape).border(2.dp, RojoAcento, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("X", color = RojoAcento, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Image(
                    painter = painterResource(id = R.drawable.tareas_miembros),
                    contentDescription = "Tareas por miembro",
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentScale = ContentScale.Fit
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Image(
                    painter = painterResource(id = R.drawable.distribucion_tareas),
                    contentDescription = "Distribucion",
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))
                
                Image(
                    painter = painterResource(id = R.drawable.evaluacion_mensual),
                    contentDescription = "Evolución Mensual",
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentScale = ContentScale.Fit
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                Text("Detalle por Miembro", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(48.dp).clip(CircleShape).background(VerdePrimario), 
                                contentAlignment = Alignment.Center
                            ) {
                                Text("M", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("María", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                Text("50 tareas totales", fontSize = 12.sp, color = GrisTexto)
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("45", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VerdePrimario)
                            Text("completadas", fontSize = 12.sp, color = GrisTexto)
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

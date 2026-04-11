package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.foundation.clickable

val VerdePrimario = Color(0xFF2E854B)
val FondoVerde = Color(0xFF1C7430)
val VerdeClaro = Color(0xFFE8F5E9)
val RojoAcento = Color(0xFFE53935)
val RojoClaro = Color(0xFFFFEBEE)
val AzulAcento = Color(0xFF1976D2)
val AzulClaro = Color(0xFFE3F2FD)
val GrisTexto = Color(0xFF555555)
val GrisSuperficie = Color(0xFFFCFCFC)

@Preview
@Composable
fun PantallaEstadisticas(onBackClick: () -> Unit = {}) {
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
                        modifier = Modifier.size(32.dp).clickable { onBackClick() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Task",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "ify",
                            color = Color(0xFF4ADE80),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
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
                text = "Estadísticas generales",
                color = Color.White,
                fontSize = 26.sp,
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
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                
                val expandidoHogar = remember { mutableStateOf(false) }
                val hogarSeleccionado = remember { mutableStateOf("Todos los hogares") }
                val listaHogares = listOf("Todos los hogares", "Casa Familia González", "Apartamento Centro")

                val expandidoFecha = remember { mutableStateOf(false) }
                val fechaSeleccionada = remember { mutableStateOf("Marzo 2026") }
                val listaFechas = listOf("Enero 2026", "Febrero 2026", "Marzo 2026")

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Hogar", fontSize = 12.sp, color = GrisTexto)
                        Spacer(modifier = Modifier.height(4.dp))
                        @OptIn(ExperimentalMaterial3Api::class)
                        ExposedDropdownMenuBox(
                            expanded = expandidoHogar.value,
                            onExpandedChange = { expandidoHogar.value = !expandidoHogar.value }
                        ) {
                            OutlinedTextField(
                                value = hogarSeleccionado.value,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoHogar.value) },
                                modifier = Modifier.fillMaxWidth().height(48.dp).menuAnchor(),
                                shape = RoundedCornerShape(8.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                            )
                            ExposedDropdownMenu(
                                expanded = expandidoHogar.value,
                                onDismissRequest = { expandidoHogar.value = false }
                            ) {
                                listaHogares.forEach { seleccion ->
                                    DropdownMenuItem(
                                        text = { Text(seleccion) },
                                        onClick = {
                                            hogarSeleccionado.value = seleccion
                                            expandidoHogar.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Fecha", fontSize = 12.sp, color = GrisTexto)
                        Spacer(modifier = Modifier.height(4.dp))
                        @OptIn(ExperimentalMaterial3Api::class)
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
                            Text("Hogares", color = Color.White, fontSize = 12.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("3", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.5f),
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
                            Text("Completadas", color = GrisTexto, fontSize = 12.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("287", color = VerdePrimario, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
                            contentScale = ContentScale.Fit,
                            alpha = 0.8f
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Tasa de Incompleción", color = GrisTexto, fontSize = 12.sp)
                            Text("15.3%", color = RojoAcento, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("No completadas", color = GrisTexto, fontSize = 12.sp)
                            Text("52", color = RojoAcento, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Card(
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.grafica1),
                            contentDescription = "Porcentaje de Completadas",
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Card(
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.grafica2),
                            contentDescription = "Comparación Mensual",
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Detalle por Hogar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(VerdePrimario), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Casa Familia González", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                Text("189 tareas totales", fontSize = 12.sp, color = GrisTexto)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(VerdeClaro).padding(8.dp)) {
                                Column {
                                    Text("Completadas", fontSize = 10.sp, color = GrisTexto)
                                    Text("160", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VerdePrimario)
                                }
                            }
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(RojoClaro).padding(8.dp)) {
                                Column {
                                    Text("Pendientes", fontSize = 10.sp, color = GrisTexto)
                                    Text("29", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = RojoAcento)
                                }
                            }
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(AzulClaro).padding(8.dp)) {
                                Column {
                                    Text("Éxito", fontSize = 10.sp, color = GrisTexto)
                                    Text("85%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AzulAcento)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEEEEEE))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(VerdePrimario), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Apartamento Centro", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                Text("100 tareas totales", fontSize = 12.sp, color = GrisTexto)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(VerdeClaro).padding(8.dp)) {
                                Column {
                                    Text("Completadas", fontSize = 10.sp, color = GrisTexto)
                                    Text("85", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VerdePrimario)
                                }
                            }
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(RojoClaro).padding(8.dp)) {
                                Column {
                                    Text("Pendientes", fontSize = 10.sp, color = GrisTexto)
                                    Text("15", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = RojoAcento)
                                }
                            }
                            Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(AzulClaro).padding(8.dp)) {
                                Column {
                                    Text("Éxito", fontSize = 10.sp, color = GrisTexto)
                                    Text("85%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AzulAcento)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(VerdePrimario)
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Resumen del Período", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total de tareas asignadas", color = Color.White, fontSize = 12.sp)
                            Text("339", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Promedio por hogar", color = Color.White, fontSize = 12.sp)
                            Text("113", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Tasa de éxito global", color = Color.White, fontSize = 12.sp)
                            Text("84.7%", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

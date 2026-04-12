package orlando.leyva.proyectofinal_equipo2.ui.screens

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.ui.components.MemberSelectionDialog
import orlando.leyva.proyectofinal_equipo2.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormTarea(
    modoEditar: Boolean = false,
    onBackClick: () -> Unit = {},
    onAceptarClick: () -> Unit = {}
) {
    var nombreTarea by remember { mutableStateOf(if (modoEditar) "Pasear al perro" else "") }
    var descripcionTarea by remember { mutableStateOf(if (modoEditar) "Sacar a pasear al Roky por la colonia durante 30 minutos." else "") }
    var tipoPredeterminada by remember { mutableStateOf(false) } // false = Manual (Dias semana), true = Predeterminada (Calendario)
    var recurrencia by remember { mutableStateOf("Semanal") }
    var divisionEquipo by remember { mutableStateOf(false) } // false = Equipo, true = Dias
    var habitacionSeleccionada by remember { mutableStateOf("Toda la casa") }
    
    val diasSemana = listOf("L", "M", "X", "J", "V", "S", "D")

    // TODO: En una nueva tarea, hacer que se seleccione el dia actual del telefono por defecto
    val diasSeleccionados = remember {
        mutableStateListOf<String>().apply {
            if (modoEditar) addAll(listOf("L", "M", "X"))
        }
    }

    // Estado para el DatePicker
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var fechaSeleccionadaStr by remember { mutableStateOf("Seleccionar dia") }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val date = Date(millis)
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        fechaSeleccionadaStr = formatter.format(date)
                    }
                    showDatePicker = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Estado para el Dropdown de Recurrencia
    var expandedRecurrencia by remember { mutableStateOf(false) }
    val opcionesRecurrencia = if (tipoPredeterminada) {
        listOf("Solo una vez", "Semanal", "Mensual", "Anual")
    } else {
        listOf("Semanal", "Mensual", "Anual")
    }
    
    // Asegurar que la recurrencia sea valida si cambia el tipo
    LaunchedEffect(tipoPredeterminada) {
        if (!tipoPredeterminada && recurrencia == "Solo una vez") {
            recurrencia = "Semanal"
        }
    }

    // Estado para el dialogo de seleccion de miembros
    var showMemberDialog by remember { mutableStateOf(false) }
    val miembrosSeleccionados = remember { mutableStateListOf<String>() }
    
    val textoMiembros = if (miembrosSeleccionados.isEmpty()) {
        "Ningúno."
    } else {
        val primerosDos = miembrosSeleccionados.take(2).joinToString(", ")
        if (miembrosSeleccionados.size > 2) {
            "$primerosDos ...${miembrosSeleccionados.size - 2} más."
        } else {
            primerosDos
        }
    }

    // Logica para bloquear el switch de "Dias"
    val puedeDividirPorDias = !tipoPredeterminada && diasSeleccionados.size >= 2
    
    // Si ya no puede dividir por dias, regresamos a "Equipo"
    LaunchedEffect(puedeDividirPorDias) {
        if (!puedeDividirPorDias) {
            divisionEquipo = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoVerde)
    ) {
        // Cabecera identica a PantallaEstadisticas.kt
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
        }

        // Formulario
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (modoEditar) "Editar tarea" else "Agregar tarea",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo
                FormLabel("Tipo")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Predeterminada", fontWeight = FontWeight.Medium)
                    Switch(
                        checked = tipoPredeterminada,
                        onCheckedChange = { tipoPredeterminada = it },
                        modifier = Modifier.padding(horizontal = 8.dp),
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Color.Black)
                    )
                    Text("Manual", fontWeight = FontWeight.Medium)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                // Nombre
                FormLabel("Nombre")
                TextField(
                    value = nombreTarea,
                    onValueChange = { nombreTarea = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe el nombre de la tarea...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = GrisFondoCampos,
                        unfocusedContainerColor = GrisFondoCampos,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(4.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                // Descripcion
                FormLabel("Descripción")
                TextField(
                    value = descripcionTarea,
                    onValueChange = { descripcionTarea = it },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 80.dp),
                    placeholder = { Text("Describe la tarea...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = GrisFondoCampos,
                        unfocusedContainerColor = GrisFondoCampos,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(4.dp),
                    maxLines = 4
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                // Dia de la semana
                FormLabel("Día de la semana")
                if (tipoPredeterminada) {
                    Button(
                        onClick = { showDatePicker = true },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(fechaSeleccionadaStr, color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        diasSemana.forEach { dia ->
                            val seleccionado = diasSeleccionados.contains(dia)
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (seleccionado) VerdePrimario else GrisFondoCampos)
                                    .clickable {
                                        if (seleccionado) diasSeleccionados.remove(dia)
                                        else diasSeleccionados.add(dia)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dia,
                                    color = if (seleccionado) Color.White else GrisTextoSecundario,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Recurrencia
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    FormLabel("Recurrencia", modifier = Modifier.padding(bottom = 0.dp).width(IntrinsicSize.Min))
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Box {
                        Box(
                            modifier = Modifier
                                .border(1.dp, GrisBorde, RoundedCornerShape(8.dp))
                                .clickable { expandedRecurrencia = true }
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(recurrencia)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        }
                        DropdownMenu(
                            expanded = expandedRecurrencia,
                            onDismissRequest = { expandedRecurrencia = false }
                        ) {
                            opcionesRecurrencia.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion) },
                                    onClick = {
                                        recurrencia = opcion
                                        expandedRecurrencia = false
                                    }
                                )
                            }
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                // Miembros
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    FormLabel("Miembros", modifier = Modifier.padding(bottom = 0.dp).width(IntrinsicSize.Min))
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { showMemberDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Text("Agregar Miembro", fontSize = 12.sp)
                    }
                }
                Text(
                    text = textoMiembros,
                    color = GrisTextoSecundario,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )

                // Solo mostrar Division de Labores si hay 2 o más miembros seleccionados
                if (miembrosSeleccionados.size >= 2) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                    // Division de labores
                    FormLabel("División de labores")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Equipo", fontWeight = FontWeight.Medium)
                        Switch(
                            checked = divisionEquipo,
                            onCheckedChange = { divisionEquipo = it },
                            modifier = Modifier.padding(horizontal = 8.dp),
                            enabled = puedeDividirPorDias, // Bloqueado si no hay al menos 2 dias o si es modo calendario
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White, 
                                checkedTrackColor = Color.Black,
                                disabledCheckedTrackColor = Color.LightGray.copy(alpha = 0.5f)
                            )
                        )
                        Text("Días", fontWeight = FontWeight.Medium, color = if (puedeDividirPorDias) Color.Unspecified else Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tabla de labores (Solo si se selecciona "Dias")
                    if (divisionEquipo) {
                        // Ordenamos los dias seleccionados segun el orden de la semana
                        val diasOrdenados = diasSemana.filter { diasSeleccionados.contains(it) }
                        TableLabores(miembros = miembrosSeleccionados, dias = diasOrdenados)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GrisBorde)

                // Habitacion
                FormLabel("Habitación")
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = habitacionSeleccionada == "No aplica",
                            onClick = { habitacionSeleccionada = "No aplica" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                        )
                        Text("No aplica")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = habitacionSeleccionada == "Toda la casa",
                            onClick = { habitacionSeleccionada = "Toda la casa" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                        )
                        Text("Toda la casa")
                    }
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Seleccionar habitación")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botones Finales
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { onBackClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = RojoBoton),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancelar", color = Color.White)
                    }
                    Button(
                        onClick = { onAceptarClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Aceptar", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (showMemberDialog) {
        MemberSelectionDialog(
            currentSelected = miembrosSeleccionados,
            onDismiss = { showMemberDialog = false },
            onConfirm = { seleccion ->
                miembrosSeleccionados.clear()
                miembrosSeleccionados.addAll(seleccion)
                showMemberDialog = false
            }
        )
    }
}

@Composable
fun FormLabel(text: String, modifier: Modifier = Modifier.padding(bottom = 8.dp)) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun TableLabores(miembros: List<String>, dias: List<String>) {
    // Mapa para distribuir los dias de forma equitativa
    val asignaciones = mutableMapOf<String, MutableList<String>>()
    miembros.forEach { asignaciones[it] = mutableListOf() }
    
    // Distribucion round-robin
    dias.forEachIndexed { index, dia ->
        val miembro = miembros[index % miembros.size]
        asignaciones[miembro]?.add(dia)
    }

    // Convertimos las abreviaturas a nombres completos o mantenemos las letras segun prefieras.
    // Aqui usaremos los nombres de los miembros seleccionados.

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, GrisBorde)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrisFondoCampos)
                .padding(8.dp)
        ) {
            Text("Miembro", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Text("Días", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
        
        // Filas dinamicas basadas en los miembros seleccionados
        miembros.forEach { miembro ->
            HorizontalDivider(color = GrisBorde)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = miembro, 
                    modifier = Modifier.weight(1f), 
                    textAlign = TextAlign.Center, 
                    color = GrisTextoSecundario,
                    fontSize = 14.sp
                )
                
                val diasDelMiembro = asignaciones[miembro]?.joinToString(", ") ?: "---"
                Text(
                    text = diasDelMiembro, 
                    modifier = Modifier.weight(1f), 
                    textAlign = TextAlign.Center, 
                    color = GrisTextoSecundario,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPantallaFormTarea() {
    PantallaFormTarea()
}

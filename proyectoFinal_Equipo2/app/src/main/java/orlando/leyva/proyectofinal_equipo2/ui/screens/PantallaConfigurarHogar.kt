package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import orlando.leyva.proyectofinal_equipo2.R
import orlando.leyva.proyectofinal_equipo2.ui.components.BottomBar
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaConfigurarHogarPreview() {
    val navController = rememberNavController()
    PantallaConfigurarHogar(navController = navController)
}

@Composable
fun PantallaConfigurarHogar(
    navController: NavController
) {

    val gradienteFondo = Brush.horizontalGradient(
        colors = listOf(VerdeOscuro, VerdeClaro)
    )

    var permisos by remember { mutableStateOf(true) }

    var habitaciones by remember {
        mutableStateOf(listOf("Sala", "Cocina", "Dormitorio"))
    }

    var tareas by remember {
        mutableStateOf(
            listOf(
                "Sacar la basura",
                "Lavar los platos",
                "Limpiar el baño"
            )
        )
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradienteFondo)
                .padding(top = padding.calculateTopPadding())
        ) {

            Column {

                Header(
                    titulo = "Taskify",
                    mostrarBack = true,
                    onBack = { navController.popBackStack() }
                )

                Text(
                    text = "Configurar Hogar",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-25).dp),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        contentPadding = PaddingValues(bottom = 70.dp)
                    ) {

                        // CASA
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF1E4DB7), RoundedCornerShape(12.dp))
                                    .padding(18.dp)
                            ) {

                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Icon(
                                        painter = painterResource(R.drawable.house),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp),
                                        tint = Color.White
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Casa Familia Leyva", fontWeight = FontWeight.Bold,color = Color.White, fontSize = 20.sp)
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text("Color de casa: Azul",color = Color.White, fontSize = 16.sp)
                                    }
                                }

                                Icon(
                                    painter = painterResource(R.drawable.editarcasa),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .size(25.dp),
                                    tint = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // PERMISOS
                        item {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFE4E3E1)),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Ajustar Permisos de Edición", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                        Text("Permitir a los miembros editar tareas, habitaciones y otros ajustes.", fontSize = 14.sp)
                                    }

                                    Switch(
                                        checked = permisos,
                                        onCheckedChange = { permisos = it },
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.White,
                                            checkedTrackColor = Color(0xFF4CAF50),
                                            uncheckedThumbColor = Color.White,
                                            uncheckedTrackColor = Color.Gray
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // HABITACIONES
                        item {
                            SeccionBloque(
                                titulo = "Habitaciones",
                                items = habitaciones,
                                onAdd = { habitaciones = habitaciones + "Nueva" },
                                onDelete = { habitaciones = habitaciones - it },
                                onEdit = { viejo, nuevo ->
                                    habitaciones = habitaciones.map {
                                        if (it == viejo) nuevo else it
                                    }
                                }
                            )
                        }

                        item { Spacer(modifier = Modifier.height(12.dp)) }

                        // TAREAS
                        item {
                            SeccionBloque(
                                titulo = "Tareas\nPredeterminadas",
                                items = tareas,
                                onAdd = { tareas = tareas + "Nueva tarea" },
                                onDelete = { tareas = tareas - it },
                                onEdit = { viejo, nuevo ->
                                    tareas = tareas.map {
                                        if (it == viejo) nuevo else it
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeccionBloque(
    titulo: String,
    items: List<String>,
    onAdd: () -> Unit,
    onDelete: (String) -> Unit,
    onEdit: (String, String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onAdd,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                modifier = Modifier.height(34.dp).width(140.dp)
            ) {
                Text("+ Agregar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        items.forEach { item ->
            ItemListaCompacto(
                texto = item,
                onDelete = { onDelete(item) },
                onEditConfirm = { nuevo -> onEdit(item, nuevo) }
            )
        }
    }
}

@Composable
fun ItemListaCompacto(
    texto: String,
    onDelete: () -> Unit,
    onEditConfirm: (String) -> Unit
) {

    var editando by remember { mutableStateOf(false) }
    var textoEditado by remember(texto) { mutableStateOf(texto) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp) // 🔥 cambio clave
            .padding(vertical = 6.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(6.dp))

        if (editando) {
            TextField(
                value = textoEditado,
                onValueChange = { textoEditado = it },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        } else {
            Text(
                texto,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp
            )
        }

        IconButton(
            onClick = {
                if (editando) {
                    onEditConfirm(textoEditado)
                }
                editando = !editando
            }
        ) {
            Icon(
                imageVector = if (editando) Icons.Default.Check else Icons.Default.Edit,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = Color.Unspecified
            )
        }
    }
}


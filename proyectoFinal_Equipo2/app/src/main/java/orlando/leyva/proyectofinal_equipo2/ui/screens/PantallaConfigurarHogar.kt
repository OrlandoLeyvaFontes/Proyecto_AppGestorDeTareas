package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import orlando.leyva.proyectofinal_equipo2.ui.components.BottomBar
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro
import orlando.leyva.proyectofinal_equipo2.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaConfigurarHogarPreview() {
    PantallaConfigurarHogar()
}


@Composable
fun PantallaConfigurarHogar() {

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
        bottomBar = { BottomBar() }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradienteFondo)
                .padding(top = padding.calculateTopPadding())
        ) {

            Column {

                Header("Taskify")

                Text(
                    text = "Configurar Hogar",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-25).dp),

                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        // 🔹 CASA (con fondo gris)
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFE4E3E1), RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFE4E3E1), RoundedCornerShape(8.dp))
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        painter = painterResource(R.drawable.house),
                                        contentDescription = null,
                                        modifier = Modifier.size(26.dp),
                                        tint = Color.Unspecified
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            "Casa Familia Leyva",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "Color: Blanco",
                                            fontSize = 12.sp
                                        )
                                    }

                                    Icon(
                                        painter = painterResource(R.drawable.editarcasa),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // 🔹 PERMISOS
                        item {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Ajustar Permisos", fontWeight = FontWeight.Bold)
                                        Text("Permitir a los miembros editar", fontSize = 12.sp)
                                    }

                                    Switch(
                                        checked = permisos,
                                        onCheckedChange = { permisos = it }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // 🔹 HABITACIONES
                        item {
                            SeccionBloque(
                                titulo = "Habitaciones",
                                items = habitaciones,
                                onAdd = { habitaciones = habitaciones + "Nueva" },
                                onDelete = { habitaciones = habitaciones - it }
                            )
                        }

                        item { Spacer(modifier = Modifier.height(12.dp)) }

                        // 🔹 TAREAS
                        item {
                            SeccionBloque(
                                titulo = "Tareas Predeterminadas",
                                items = tareas,
                                onAdd = { tareas = tareas + "Nueva tarea" },
                                onDelete = { tareas = tareas - it }
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
    onDelete: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(titulo, fontWeight = FontWeight.Bold)

            Button(
                onClick = onAdd,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(34.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                Text("+ Agregar", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        items.forEach { item ->
            ItemListaCompacto(
                texto = item,
                onDelete = { onDelete(item) }
            )
        }
    }
}

@Composable
fun ItemListaCompacto(
    texto: String,
    onDelete: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            texto,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )

        Icon(
            painter = painterResource(R.drawable.editarcasa),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(6.dp))

        IconButton(
            onClick = onDelete,
            modifier = Modifier.size(28.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Red
            )
        }
    }
}



package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PantallaRegistro(onBackToLogin: () -> Unit = {}, onRegisterClick: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    val fondoClaro = Color(0xFFF9F9F9)
    val colorVerde = Color(0xFF2E854B)
    
    var nombre by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    val expandidoGenero = remember { mutableStateOf(false) }
    val opcionesGenero = listOf("Masculino", "Femenino", "Otro")
    
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoClaro)
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_login),
                    contentDescription = "Logotipo",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text("Task", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("ify", color = colorVerde, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(100.dp)
                    .background(colorVerde, RoundedCornerShape(2.dp))
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text("Registro", color = colorVerde, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Nombre", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            OutlinedTextField(
                value = nombre, onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Genero", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            ExposedDropdownMenuBox(
                expanded = expandidoGenero.value,
                onExpandedChange = { expandidoGenero.value = !expandidoGenero.value }
            ) {
                OutlinedTextField(
                    value = genero, onValueChange = {}, readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoGenero.value) },
                    modifier = Modifier.fillMaxWidth().height(48.dp).menuAnchor().background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
                )
                ExposedDropdownMenu(
                    expanded = expandidoGenero.value,
                    onDismissRequest = { expandidoGenero.value = false }
                ) {
                    opcionesGenero.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = { genero = opcion; expandidoGenero.value = false }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            val mostrarCalendario = remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()

            if (mostrarCalendario.value) {
                DatePickerDialog(
                    onDismissRequest = { mostrarCalendario.value = false },
                    confirmButton = {
                        TextButton(onClick = {
                            mostrarCalendario.value = false
                            val ms = datePickerState.selectedDateMillis
                            if (ms != null) {
                                val cal = java.util.Calendar.getInstance()
                                cal.timeInMillis = ms
                                val day = cal.get(java.util.Calendar.DAY_OF_MONTH)
                                val month = cal.get(java.util.Calendar.MONTH) + 1
                                val year = cal.get(java.util.Calendar.YEAR)
                                fechaNacimiento = "${day.toString().padStart(2, '0')}/${month.toString().padStart(2, '0')}/$year"
                            }
                        }) { Text("Aceptar") }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarCalendario.value = false }) { Text("Cancelar") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            Text("Fecha de nacimiento", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Box {
                OutlinedTextField(
                    value = fechaNacimiento, onValueChange = {},
                    readOnly = true,
                    trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Calendario") },
                    modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
                )
                Box(modifier = Modifier.matchParentSize().clickable { mostrarCalendario.value = true })
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Correo electrónico", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            OutlinedTextField(
                value = correo, onValueChange = { correo = it },
                modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Contraseña", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            OutlinedTextField(
                value = contrasena, onValueChange = { contrasena = it },
                modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Confirmar contraseña", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            OutlinedTextField(
                value = confirmarContrasena, onValueChange = { confirmarContrasena = it },
                modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray, focusedBorderColor = colorVerde)
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Button(
            onClick = {
                Toast.makeText(context, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show()
                onRegisterClick()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorVerde),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Registrarse", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("¿Ya tienes cuenta? ", fontSize = 12.sp, color = Color.DarkGray)
            Text(
                "Inicia Sesión", 
                fontSize = 12.sp, 
                color = Color.Black, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackToLogin() }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

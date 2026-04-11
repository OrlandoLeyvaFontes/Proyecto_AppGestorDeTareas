package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PantallaLogin(onLogin: () -> Unit = {}, onRegister: () -> Unit = {}) {
    val fondoClaro = Color(0xFFF9F9F9)
    val colorVerde = Color(0xFF2E854B)
    
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoClaro)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Spacer(modifier = Modifier.weight(1f))
        
        Image(
            painter = painterResource(id = R.drawable.logo_login),
            contentDescription = "Logotipo Login",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Task", color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text("ify", color = colorVerde, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Correo", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier.fillMaxWidth().background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorVerde
                )
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Contraseña", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                modifier = Modifier.fillMaxWidth().background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorVerde
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text("Olvidaste tu contraseña? ", fontSize = 12.sp, color = Color.DarkGray)
                Text("Recuperar", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
        
        if (mensajeError != null) {
            Text(
                text = mensajeError!!,
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val expresionCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()
                if (correo.isBlank()) {
                    mensajeError = "Por favor ingresa tu correo electrónico."
                } else if (!correo.matches(expresionCorreo)) {
                    mensajeError = "El correo electrónico no es válido."
                } else if (contrasena.isBlank()) {
                    mensajeError = "Por favor ingresa tu contraseña."
                } else {
                    mensajeError = null
                    onLogin()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorVerde),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Iniciar Sesión", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("No tienes cuenta? ", fontSize = 12.sp, color = Color.DarkGray)
            Text(
                "Registrate", 
                fontSize = 12.sp, 
                color = Color.Black, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onRegister() }
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
    }
}

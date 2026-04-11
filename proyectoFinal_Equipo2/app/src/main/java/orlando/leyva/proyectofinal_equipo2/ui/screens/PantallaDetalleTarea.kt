package orlando.leyva.proyectofinal_equipo2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orlando.leyva.proyectofinal_equipo2.ui.components.BottomBar
import orlando.leyva.proyectofinal_equipo2.ui.components.Header
import orlando.leyva.proyectofinal_equipo2.ui.theme.ColorCompletar
import orlando.leyva.proyectofinal_equipo2.ui.theme.ColorGrisBotones
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeClaro
import orlando.leyva.proyectofinal_equipo2.ui.theme.VerdeOscuro


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaDetalleTareaPreview() {
    PantallaDetalleTarea()
}


@Composable
fun PantallaDetalleTarea() {

    // MISMO gradiente que HomeScreen
    val gradienteFondo = Brush.horizontalGradient(
        colors = listOf(VerdeOscuro, VerdeClaro)
    )

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tarea - Pagar Luz",
                        color = Color.White,
                        fontSize = 20.sp

                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(16.dp))

                // MISMO Card que HomeScreen
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-25).dp),
                    shape = RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp
                    )
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "Detalle",

                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row {
                            Text(
                                text = "Día:",
                                fontSize = 20.sp,
                                modifier = Modifier.width(40.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Lunes",
                                fontSize = 20.sp,)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            Text(
                                text = "Estado:",
                                fontSize = 20.sp,
                                modifier = Modifier.width(70.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Pendiente",
                                fontSize = 20.sp,
                                color = Color(0xFFFFC107)

                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            Text(
                                text = "Miembros asignados:",
                                fontSize = 20.sp,
                                modifier = Modifier.width(210.dp),
                                fontWeight = FontWeight.Bold

                            )

                        }
                        Text(text = "Orlando Leyva",
                            fontSize = 20.sp,)

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(text = "Descripción:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                            )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.LightGray,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp)
                                .height(120.dp)
                        ) {
                            Text(text = "Se debe pagar el recibo de la luz el día lunes por la mañana",
                                fontSize = 18.sp,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)

                                )

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // BOTONES EDITAR / PAUSAR
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        ) {

                            Button(
                                onClick = { },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.LightGray,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(45.dp)
                            ) {
                                Text(
                                    text = "Editar tarea",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Button(
                                onClick = { },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.LightGray,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(45.dp)
                            ) {
                                Text(
                                    text = "Pausar tarea",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // BOTÓN COMPLETAR
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ColorCompletar
                            ),
                            modifier = Modifier
                                .width(200.dp)
                                .height(45.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Completar tarea",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        // BOTÓN ELIMINAR
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),
                            modifier = Modifier
                                .width(180.dp)
                                .height(45.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Eliminar tarea",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                                )
                        }
                    }
                }
            }
        }
    }
}
package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Santiago Chevez
 * Muestra aviso de privacidad
 */

@Composable
fun AvisoyLeyenda(){
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary)
        .verticalScroll(scrollState))
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ){
            Text("Aviso de privacidad y leyenda de devolución",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =30.sp,
                lineHeight = 40.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("AVISO DE PRIVACIDAD",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("En Fundación Todas Brillamos AC, valoramos la privacidad de nuestros cli" +
                    "entes y nos comprometemos a proteger la información personal que nos proporcion" +
                    "an. Esta política de privacidad explica cómo recopilamos, utilizamos y protegem" +
                    "os sus datos personales.",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("INFORMACIÓN RECOLECTADA",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Datos personales: nombre, dirección, correo electrónico, número de teléfono.\n\n" +
                    "Información de pago: tarjeta de crédito, débito o PayPal",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("USO DE LA INFORMACIÓN",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Procesar y enviar pedidos\n\n" +
                    "Enviar correos electrónicos con promociones y ofertas especiales\n\n" +
                    "Mejorar nuestra tienda online y experiencia de usuario",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("PROTECCIÓN DE LA INFORMACIÓN",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Utilizamos medidas de seguridad para proteger sus datos personales\n\n" +
                    "No compartimos información personal con terceros, excepto para procesar pedidos y envíos",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("DERECHOS DE LOS CLIENTES",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Acceder, rectificar o cancelar su información personal en cualquier momento\n\n" +
                    "Oponerse al uso de su información para fines de marketing",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("CAMBIOS EN LA POLÍTICA DE PRIVACIDAD",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Podemos actualizar esta política de privacidad en cualquier momento\n\n" +
                    "Se notificará a los clientes de cualquier cambio significativo",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("FECHA DE ÚLTIMA ACTUALIZACIÓN: 2 de Septiembre 2024",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Text("Si tienes alguna pregunta o inquietud, por favor no dudes en contactarnos.\n\n" +
                    "Política de devolución: \n\n" +
                    "Por ser una prenda íntima y de uso personal, los cambios en este producto no son " +
                    "procedentes. Atendiendo a la Ley Federal de Protección al consumidor en México (PRO" +
                    "FECO) únicamente realizaremos cambios por defecto de fábrica. * Aplica únicamente de" +
                    "ntro de los primeros 5 días posteriores a la entrega *",
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                fontSize =18.sp,
                modifier =
                Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth())
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}
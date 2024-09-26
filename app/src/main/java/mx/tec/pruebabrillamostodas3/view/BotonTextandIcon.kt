package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Santiago Chevez
 * Boton con texto e icono para redes sociales y contacto
 */

@Composable
fun BotonTextandIcon(text: String, icon: ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.tertiary, fontSize: Int = 25){
    Row(modifier = modifier
        .padding(vertical = 16.dp)
        .clip(RoundedCornerShape(35.dp))
        .background(color)
        .fillMaxWidth()
        .clickable{onClick()},
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ){
        Text(text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = fontSize.sp,
            modifier = Modifier
                .padding(start = 3.dp)
                .padding(vertical = 10.dp)
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .weight(5f)
        )
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .padding(10.dp)
                .padding(end=10.dp)
                .size(50.dp)
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
                .weight(2f)
        )
    }
}
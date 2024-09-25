package mx.tec.pruebabrillamostodas3.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.sql.Blob

@Composable
fun BotonProducto(onClick: () -> Unit, imagen: ByteArray, nombre: String, precio_n: Int, precio_r: Int, rebaja: Int, modifier: Modifier = Modifier){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    var precio: Int
    if (rebaja == 1){
        precio = precio_r
    }
    else{
        precio = precio_n
    }

    Column (modifier = modifier
        .padding(vertical = 10.dp)
        .height(270.dp)
        .padding(horizontal = 5.dp)
        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(25)), horizontalAlignment = Alignment.CenterHorizontally

        ){
        ElevatedButton(
            onClick = { onClick() },
            shape = RoundedCornerShape(25),
            modifier = modifier
                .height(270.dp)
                .width(if (screenWidth < 370) 180.dp else (screenWidth/2.27).dp)
                .padding(horizontal = 3.dp)
        ) {
            Column (modifier= Modifier
                .fillMaxWidth()

            ) {
                Image(
                    painter = BitmapPainter(Image(imagen).asImageBitmap()),
                    contentDescription = "Elemento",
                    modifier = modifier.height(160.dp).fillMaxWidth()
                )
                Text(
                    text = "$" + precio.toString(),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),

                    )
                Text(
                    text = nombre,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = if (screenWidth < 500) (screenWidth/30).sp else (screenWidth/45).sp, lineHeight = 16.sp ),

                    )
            }
        }
    }
}


fun Image(image: ByteArray): Bitmap{

    val bitmap: Bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
    return bitmap
}
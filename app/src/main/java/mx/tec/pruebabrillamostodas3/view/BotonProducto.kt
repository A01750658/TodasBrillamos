package mx.tec.pruebabrillamostodas3.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.sql.Blob

@Composable
fun BotonProducto(onClick: () -> Unit, imagen: ByteArray, nombre: String, precio_n: Int, precio_r: Int, rebaja: Int, modifier: Modifier = Modifier){
    var precio: Int
    if (rebaja == 1){
        precio = precio_r
    }
    else{
        precio = precio_n
    }

    Column {
        ElevatedButton(
            onClick = { onClick() },
            shape = RoundedCornerShape(25),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            modifier = modifier
                .height(128.dp)
                .width(128.dp)
                .padding(horizontal = 3.dp)
        ) {
            Image(
                painter = BitmapPainter(Image(imagen).asImageBitmap()),
                contentDescription = "Elemento",
                modifier = modifier.fillMaxSize()
            )
        }
        Column {
            Row{
                Text(
                    text = nombre,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .width(100.dp)
                        .height(20.dp)
                )
                Text(
                    text = "$" + precio.toString(),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .width(20.dp)
                        .height(20.dp)

                )
            }
        }
    }
}

fun Image(image: ByteArray): Bitmap{

    val bitmap: Bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
    return bitmap
}
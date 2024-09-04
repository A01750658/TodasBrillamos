package mx.tec.pruebabrillamostodas3.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.sql.Blob

@Composable
fun BotonElemento(onClick: () -> Unit, imagen: Blob, nombre: String, precio_n: Int, precio_r: Int, rebaja: Int, modifier: Modifier = Modifier){
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
            modifier = modifier
                .fillMaxWidth()
                .height(128.dp)
        ) {
            Image(
                painter = BitmapPainter(BlobImage(imagen).asImageBitmap()),
                contentDescription = "Elemento",
                modifier = modifier.fillMaxSize().weight(1f)
            )
        }
        Text(text = nombre,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().weight(1f))
        Text(text = precio.toString(),
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().weight(1f))
    }
}

fun blobToByteArray(image: Blob): ByteArray{
    val blobLength = image.length().toInt()
    return image.getBytes(1, blobLength)
}

fun BlobImage(image: Blob): Bitmap{
    val byteArray = blobToByteArray(image)

    val bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return bitmap
}
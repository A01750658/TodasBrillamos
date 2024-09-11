package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Inputtexto(text: String, onValueChange: (Any?) -> Unit, modifier: Modifier = Modifier, padding: Int = 16){
    OutlinedTextField(
        value = text,
        onValueChange =onValueChange,
        modifier = modifier
            .padding(horizontal = padding.dp)
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(50.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onTertiary)
            .border(1.dp, MaterialTheme.colorScheme.onTertiary)
    )
}
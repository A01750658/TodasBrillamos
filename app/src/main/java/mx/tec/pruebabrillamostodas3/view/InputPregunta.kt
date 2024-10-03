package mx.tec.pruebabrillamostodas3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Esta función es la barra de busqueda en la que se buscan las preguntas de los usuarios
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @param text el texto que esta escribiendo el usuario
 * @param onValueChange es una función que define lo que debe de hacer cuando el usuario escribe un texto
 * @param modifier es el modificador
 * @param onDone es una función que dice que hacer cuando el usario acaba de escribir
 * @param keyBoardType es el tipo de teclado que se va a usar
 */
@Composable
fun InputPregunta(text: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, onDone: () -> Unit = {}, keyBoardType: KeyboardType = KeyboardType.Text){
    val focusManager = LocalFocusManager.current
    OutlinedTextField(value = text,
        onValueChange = {
            onValueChange(it)
            onSearch(it)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        placeholder = {
            Text("Buscar...", color = Color.LightGray)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.onTertiary),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
        onDone = {
            onDone()
            focusManager.clearFocus()
        }
    ))
}
fun onSearch(it: String){
    println("Buscando $it")

}
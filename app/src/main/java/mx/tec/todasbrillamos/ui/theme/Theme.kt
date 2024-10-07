package mx.tec.todasbrillamos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = RosaLibertad,
    primaryContainer = AzulTranquilidad,
    secondary = RosaBrillamos,
    secondaryContainer = AzulSostenible,
    tertiary = RosaConfianza,
    tertiaryContainer = AmarilloComodidad,

)

private val LightColorScheme = lightColorScheme(
    /*
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
    */
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
    primary = RosaLibertad,
    primaryContainer = VerdeSostenible,
    secondary = RosaBrillamos,
    secondaryContainer = AzulTranquilidad,
    tertiary = NaranjaConfianza,
    tertiaryContainer = AmarilloComodidad,
    onTertiary = Blanco,
    onBackground = Borde,
    inversePrimary = aviso,
    error = MoradoTranquilo,


    onPrimary = Facebook,
    onSecondary = Intagram,
    onPrimaryContainer = TikTok,
    onSecondaryContainer = Whatsapp,
    onTertiaryContainer = Youtube,

)

@Composable
fun PruebaBrillamosTodas3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
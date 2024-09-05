package mx.tec.pruebabrillamostodas3.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = RosaLibertad,
    primaryContainer = AzulTranquilidad,
    secondary = RosaBrillamos,
    secondaryContainer = VerdeSostenible,
    tertiary = NaranjaConfianza,
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
    primaryContainer = AzulTranquilidad,
    secondary = RosaBrillamos,
    secondaryContainer = VerdeSostenible,
    tertiary = NaranjaConfianza,
    tertiaryContainer = AmarilloComodidad,
    onTertiary = Blanco,

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
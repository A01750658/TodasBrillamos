package mx.tec.todasbrillamos.model

import kotlinx.serialization.Serializable

/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API

 * Formato del JSON
    {
     "id": 15,
     "nombre": "producto prueba 2",
     "descripcion": " descripción prueba 1",
     "precio_normal": 200,
     "precio_rebajado": 1,
     "cantidad": 1,
     "rebaja": 0
*        }
 */
@Serializable
data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio_normal: Int,
    val precio_rebajado: Int,
    val cantidad: Int,
    val rebaja: Int,
    val categoria : String
)
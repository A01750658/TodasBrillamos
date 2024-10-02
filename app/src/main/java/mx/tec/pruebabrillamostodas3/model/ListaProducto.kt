package mx.tec.pruebabrillamostodas3.model

import kotlinx.serialization.Serializable
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
 * Formato JSON
 *
{
"cantidad": 15,
    "productos": [
        {
        "id": 15,
        "nombre": "producto prueba 1",
        "descripcion": " descripción prueba 1",
        "precio_normal": 200,
        "precio_rebajado": 1,
        "cantidad": 1,
        "rebaja": 0
        },
        {
        "id": 15,
        "nombre": "producto prueba 2",
        "descripcion": " descripción prueba 2",
        "precio_normal": 200,
        "precio_rebajado": 1,
        "cantidad": 1,
        "rebaja": 0
        }
        .
        .
        .
        {
        "id": 15,
        "nombre": "producto prueba 2",
        "descripcion": " descripción prueba 1",
        "precio_normal": 200,
        "precio_rebajado": 1,
        "cantidad": 1,
        "rebaja": 0
        }
    ]
}

 */

@Serializable
data class ListaProducto(
    val cantidad: Int,
    val productos: List<Producto>
)
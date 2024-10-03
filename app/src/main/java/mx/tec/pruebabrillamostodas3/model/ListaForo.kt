package mx.tec.pruebabrillamostodas3.model
/**
 * @author Carlos Iker Fuentes Reyes
 * Clase de datos que se utiliza como formato para la
 * lectura del JSON en la clase abstracta Todasbrillamos API
 *
Formato JSON

{
"foros": [
        {
        "id": 161,
        "pregunta":"ejemplo pregunta"
        },
        {
        "id": 121,
        "pregunta": "pregunta 2"
        }
    ]
}
 * */

data class ListaForo(

    val foros: List<Foro>
)

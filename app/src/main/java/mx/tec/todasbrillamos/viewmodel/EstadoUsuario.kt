package mx.tec.todasbrillamos.viewmodel

import mx.tec.todasbrillamos.model.Direccion

/**
 * Clase que contiene los atributos del estado del usuario en la aplicación.
 * Contiene los atributos necesarios para el registro de un usuario.
 *
 * @author Alan Vega
 * @author Santiago Chevez
 * @author Andrés Cabrera
 * @author Iker Fuentes
 * @author Cesar Flores
 *
 */

data class EstadoUsuario(
    val nombre: String = "",
    val apellido_paterno: String = "",
    val apellido_materno: String = "",
    val ano_nacimiento: Int = 0,
    val mes_nacimiento: Int = 0,
    val día_nacimiento: Int = 0,
    val telefono: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmacion_password: String = "",
    val aviso: Boolean = false,
    val marketing: Boolean = false,
    val direccion : Direccion = Direccion("","","","","0","0","0",0),
    val key : String = "",
    val id : Int = -1,
    val loading: Boolean = false,
    val intent: Boolean = false,
    val codigo: String = ""
)

package mx.tec.todasbrillamos.model

/**
 * @author Alan Rodrigo Vega Reza
 */

import com.paypal.base.rest.APIContext
import com.paypal.base.rest.OAuthTokenCredential

object PayPalConfig{
    private const val CLIENT_ID = "AVaA8e03cxh_wVdXiJ4Zz3yfQnq1d4y_0XR-_2V75Er4_YlEKn40AeUGICZZtE68-akwRUPq5L2vK_NI"
    private const val CLIENT_SECRET = "ELbBJmbOI-qkAQ72da-xOKWOQzcSw-BPylIBST7WUa_m0n8LB97JXx4Vw3JThHow1SO-Vm39XlbsDt6J"
    private const val MODE = "sandbox"

    val apiContext: APIContext by lazy {
        val configMap = mapOf(
            "mode" to MODE
        )
        val tokenCredential = OAuthTokenCredential(CLIENT_ID, CLIENT_SECRET, configMap)
        val accessToken = tokenCredential.accessToken
        APIContext(accessToken).apply{
            configurationMap = configMap
        }

    }
}
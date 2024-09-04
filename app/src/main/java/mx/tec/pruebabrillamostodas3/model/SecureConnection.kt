import io.ktor.client.HttpClient
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Application.main(httpClient: HttpClient = applicationHttpClient) {
    install(Authentication) {
        oauth("auth-oauth-oracle") {
            urlProvider = { "https://apex.oracle.com/pls/apex/todasbrillamos/connector/" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "oracle",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    clientId = "KkcFLfQx75b0prlxelmXCA..",
                    clientSecret = "483cE2SOpfNg1BbAm-zsfA.."
                )
            }
            client = httpClient
        }
    }

    routing {
        authenticate("auth-oauth-oracle") {
            get("/protected-route") {
                call.respondText("You are authenticated!")
            }
        }
    }
}

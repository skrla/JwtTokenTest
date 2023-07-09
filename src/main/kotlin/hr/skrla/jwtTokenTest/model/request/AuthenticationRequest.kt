package hr.skrla.jwtTokenTest.model.request

data class AuthenticationRequest(
    val email: String,
    val pass: String
)

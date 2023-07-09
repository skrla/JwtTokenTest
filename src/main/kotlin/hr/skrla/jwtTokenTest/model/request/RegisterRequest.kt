package hr.skrla.jwtTokenTest.model.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val pass: String
)

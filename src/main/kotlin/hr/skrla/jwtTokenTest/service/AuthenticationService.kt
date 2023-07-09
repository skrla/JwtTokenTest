package hr.skrla.jwtTokenTest.service

import hr.skrla.jwtTokenTest.model.request.AuthenticationRequest
import hr.skrla.jwtTokenTest.model.AuthenticationResponse
import hr.skrla.jwtTokenTest.model.request.RegisterRequest
import hr.skrla.jwtTokenTest.model.Role
import hr.skrla.jwtTokenTest.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import hr.skrla.jwtTokenTest.model.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    @Autowired private val repository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtService: JwtService,
    @Autowired private val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        val user = User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            pass = passwordEncoder.encode(request.pass),
            role = Role.USER
        )
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token)
    }

    fun authenticate(request: AuthenticationRequest) : AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.pass)
        )
        val user = repository.findByEmail(request.email).orElseThrow {Exception("User not found")}
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token)
    }
}
package hr.skrla.jwtTokenTest.controllers.auth

import hr.skrla.jwtTokenTest.model.request.AuthenticationRequest
import hr.skrla.jwtTokenTest.model.AuthenticationResponse
import hr.skrla.jwtTokenTest.model.request.RegisterRequest
import hr.skrla.jwtTokenTest.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    @Autowired private val authService: AuthenticationService
){

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authService.authenticate(request))
    }
}
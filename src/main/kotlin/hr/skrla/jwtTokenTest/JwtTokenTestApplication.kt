package hr.skrla.jwtTokenTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtTokenTestApplication

fun main(args: Array<String>) {
	runApplication<JwtTokenTestApplication>(*args)
}

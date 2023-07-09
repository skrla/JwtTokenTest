package hr.skrla.jwtTokenTest.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/greetings")
class GreetingsController {

    @GetMapping
    fun sayHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello from our API")
    }

    @GetMapping("/good-bye")
    fun sayGoodBye(): ResponseEntity<String> {
        return ResponseEntity.ok("Good bye and see you later")
    }
}
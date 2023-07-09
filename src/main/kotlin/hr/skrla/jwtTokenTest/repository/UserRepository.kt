package hr.skrla.jwtTokenTest.repository

import hr.skrla.jwtTokenTest.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): Optional<User>
}
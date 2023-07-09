package hr.skrla.jwtTokenTest.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Service
class JwtService {

    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String? = null

    fun extractUsername(token: String): String {
        return extractClaims(token, Claims::getSubject) ?: ""
    }

    fun generateToken(extraClaims: Map<String, JvmType.Object>, userDetails: UserDetails): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(emptyMap(), userDetails)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUsername(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token)?.before(Date()) ?: true
    }

    private fun extractExpiration(token: String): Date? {
        return extractClaims(token, Claims::getExpiration)
    }

    fun <T> extractClaims(token: String, claimsResolver: (Claims) -> T) : T? {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String) : Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignInKey(): Key {
        val keyByte = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyByte)
    }
}


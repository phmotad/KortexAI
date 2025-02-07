package com.kortexai.framework.auth

class AuthenticationService {
    private val tokenManager = JWTManager(Secret.KEY)
    
    fun authenticate(credentials: Credentials): AuthToken {
        validateCredentials(credentials)
        return tokenManager.generateToken(credentials.username)
    }
    
    fun authorize(token: AuthToken, requiredRole: Role): Boolean {
        val claims = tokenManager.validateToken(token)
        return claims.roles.contains(requiredRole)
    }
}

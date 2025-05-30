package br.com.fiap.Ayra.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.Ayra.controller.AuthController.Token;
import br.com.fiap.Ayra.model.User;

@Service
public class TokenService {

    // Define a validade do token (2 horas)
    private Instant expiresAt = LocalDateTime.now().plusMinutes(120).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret");

    /**
     * Cria um token JWT para o usuário.
     *
     * @param user O usuário para o qual o token será gerado.
     * @return Um objeto Token contendo o JWT e o email do usuário.
     */
    public Token createToken(User user) {
        var jwt = JWT.create()
                .withSubject(user.getId().toString()) // Usa o ID do usuário como subject
                .withClaim("email", user.getEmail())  // Armazena o email como claim
                .withExpiresAt(expiresAt)            // Define a data de expiração
                .sign(algorithm);                    // Assina o token

        return new Token(jwt, user.getEmail());
    }

    /**
     * Recupera os dados do usuário a partir de um token JWT.
     *
     * @param token O token JWT fornecido.
     * @return Um objeto User com os dados básicos do usuário.
     */
    public User getUserFromToken(String token) {
        var verifiedToken = JWT.require(algorithm).build().verify(token);

        // Constrói o objeto User com base nos dados do token
        return User.builder()
                .id(Long.valueOf(verifiedToken.getSubject())) // Recupera o ID do usuário
                .email(verifiedToken.getClaim("email").asString()) // Recupera o email
                .build();
    }
}
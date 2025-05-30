package br.com.fiap.Ayra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Ayra.model.User;
import br.com.fiap.Ayra.service.TokenService;

@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
@RestController
public class AuthController {

    public record Token(String token, String email) {}

    public record Credentials(String email, String password) {}

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    @Operation(
        summary = "Autenticar usuário",
        description = "Realiza a autenticação do usuário com base nas credenciais fornecidas (email e senha). Retorna um token JWT em caso de sucesso.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Credenciais do usuário (email e senha)",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Credentials.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Autenticação bem-sucedida. Retorna um token JWT.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Token.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Credenciais inválidas ou ausentes",
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno do servidor",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    public Token login(@RequestBody Credentials credentials) {
       System.out.println("Tentando autenticar usuário: " + credentials.email());

    var authentication = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
    try {
        var user = (User) authManager.authenticate(authentication).getPrincipal();
        System.out.println("Autenticação bem-sucedida para: " + user.getEmail());
        return tokenService.createToken(user);
    } catch (Exception e) {
        System.out.println("Erro na autenticação: " + e.getMessage());
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
    }
    }
}
package br.com.fiap.Ayra.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Gera getters, setters, equals, hashCode e toString
@Builder // Habilita o padrão Builder
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Table(name = "user_table")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "name", nullable = false)
    private String name;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Size(min = 5, message = "A senha deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A senha é obrigatória")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(min = 10, message = "O telefone deve ter pelo menos 10 dígitos")
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id_cor", nullable = false)
    private Coordinates coordinates;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
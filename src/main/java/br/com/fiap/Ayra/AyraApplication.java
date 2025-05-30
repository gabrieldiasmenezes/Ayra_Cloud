package br.com.fiap.Ayra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "AYRA", version = "v1", description = "API do sistema de prevenção de eventos extremos da natureza"))
public class AyraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyraApplication.class, args);
	}

}

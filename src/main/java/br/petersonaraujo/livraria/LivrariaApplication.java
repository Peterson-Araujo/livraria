package br.petersonaraujo.livraria;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API REST - Livraria",
				version = "1.0",
				description = "Documentação da API gerada com Swagger e Spring Boot",
				contact = @Contact(
						name = "Peterson Araujo",
						email = "petersonaraujo70@gmail.com"
				)
		)
)
@SpringBootApplication
public class LivrariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

}

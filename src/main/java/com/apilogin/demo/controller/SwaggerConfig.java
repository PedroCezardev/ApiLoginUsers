package com.apilogin.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api para Registro e Login de usuários")
                        .version("1.0.0")
                        .description("Esta Api foi criada como um exemplo para Apis de configuração de Cadastro e Autenticação de usuários, para uma plataforma ou serviço.")
                        .contact(new Contact()
                            .name("Pedro Cezar")
                            .url("https://meu-portifolio-lime.vercel.app/")
                            .email("pcdasilvabeserra@gmail.com")));
    }
}
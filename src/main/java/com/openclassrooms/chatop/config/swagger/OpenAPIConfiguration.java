package com.openclassrooms.chatop.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       Server server = new Server();
       server.setUrl("http://localhost:3001");
       server.setDescription("Documentation API Rental");

       Contact myContact = new Contact();
       myContact.setName("Jane Doe");
       myContact.setEmail("your.email@gmail.com");

       Info information = new Info()
               .title("Rental Management System API")
               .version("1.0")
               .description("This API exposes endpoints to manage rentals.")
               .contact(myContact);
       return new OpenAPI().info(information).servers(List.of(server));
   }
}
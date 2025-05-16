package com.ashokit.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
     info = @Info(
    		 title = "Employee REST Endpoints" ,
             version = "1.0",
             description = "REST API endpoints for CRUD operation",
             contact = @Contact(name="support", email="support@ashokit.in"),
             license = @License(name="Ashokit", url = "http://ashokit.in")
             ),
     servers = @Server(description="Local Server", url="http://localhost:8081")
     )
public class ApiConfig {

}

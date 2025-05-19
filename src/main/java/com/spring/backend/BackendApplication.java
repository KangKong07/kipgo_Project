package com.spring.backend;

import com.spring.backend.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@SpringBootApplication
@EntityScan(basePackageClasses = Member.class)
public class BackendApplication // implements CommandLineRunner
{

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication app  = new SpringApplication(BackendApplication.class);
        app.run(args);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        app.run(args);
    }

}
